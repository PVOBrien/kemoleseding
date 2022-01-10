package com.kml.github.kemoleseding

import android.app.DownloadManager
import android.content.*
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import java.io.*

fun fileCreateAndUri(context: Context, incomingFile: String, itsExtension: String = "pdf"): Uri {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
    lateinit var file: File

    try {
        file =
            File("${context.getExternalFilesDir("kmlPdf")}" + "/$incomingFile.pdf")
        if (!file.exists()) {
            println("in file doesn't exist block")
            inputStream = context.assets.open("pdfs/$incomingFile.$itsExtension")
            outputStream = FileOutputStream(file)
            copyFile(inputStream, outputStream)
        }
    } catch (ex: IOException) {
        println("IOException " + ex.message)
    } catch (ex: ActivityNotFoundException) {
        println(ex.message)
    } finally {
        inputStream?.close()
        outputStream?.flush()
        outputStream?.close()
    }

    return FileProvider.getUriForFile(
        context,
        "com.kml.github.kemoleseding.provider",
        file
    )
}

fun fileCreateAndUriVideo(
    context: Context,
    incomingFile: String,
    itsExtension: String = "mp4"
): Uri {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
    lateinit var file: File

    try {
        file =
            File("${context.getExternalFilesDir("Movies")}" + "/$incomingFile.$itsExtension")
        if (!file.exists()) {



            println("THIS IS fCAUV: in file doesn't exist block")
            inputStream = context.assets.open("video/$incomingFile.$itsExtension")
            outputStream = FileOutputStream(file)
            copyFile(inputStream, outputStream)
        }
    } catch (ex: IOException) {
        println("IOException " + ex.message)
    } catch (ex: ActivityNotFoundException) {
        println(ex.message)
    } finally {
        inputStream?.close()
        outputStream?.flush()
        outputStream?.close()
    }

    // ======== while getting it to DL in the first place. TODO: move back into if block. =========
    // followed https://camposha.info/android-examples/android-downloadmanager/#gsc.tab=0

    var dlId: Long = 0

    val newBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id == dlId) {
                Log.d("Download", "Complete")
            }
        }
    }

    val uri =
//        Uri.parse("https://raw.githubusercontent.com/Oclemy/SampleJSON/master/spacecrafts/voyager.jpg")
        Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
//        val uri = Uri.parse(incomingFile)

    val request = DownloadManager.Request(uri)
        .setDescription("Kml DL_Description")
        .setTitle("bbb.$itsExtension")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        .setDestinationInExternalFilesDir(context, Environment.DIRECTORY_MOVIES, "bbb.$itsExtension")

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            val uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny")

    dlId = downloadManager.enqueue(request)
//            inputStream = InputStream(URL("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny").openStream())

    context.registerReceiver(newBroadcastReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


    // ============================================

    return file.toUri()

//    return FileProvider.getUriForFile(
//        context,
//        "com.kml.github.kemoleseding.provider",
//        file
//    )
}

fun openFile(
    context: Context,
    uri: Uri
) {
    val pdfToOpen = Intent(Intent.ACTION_VIEW)
        .setDataAndType(
            uri,
            context.contentResolver.getType(uri)
        )
        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)

    val intent = Intent.createChooser(
        pdfToOpen,
        "Open Pdf using..."
    )
    context.startActivity(intent)
}


private fun copyFile(input: InputStream, output: OutputStream) {
    println("in copyfile")
    val buffer = ByteArray(1024)
    var read: Int = input.read(buffer)
    while (read != -1) {
        output.write(buffer, 0, read)
        read = input.read(buffer)
    }
}