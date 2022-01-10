package com.kml.github.kemoleseding

import android.app.DownloadManager
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
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
            inputStream = if (itsExtension == "mp4") {
                context.assets.open("video/$incomingFile.$itsExtension")
            } else {
                context.assets.open("pdfs/$incomingFile.$itsExtension")
            }
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

    val downloadManager: DownloadManager =
        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            val uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny")
    val uri =
//        Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny")
    Uri.parse("https://raw.githubusercontent.com/Oclemy/SampleJSON/master/spacecrafts/voyager.jpg")
//            val uri = Uri.parse(incomingFile)
    val request: DownloadManager.Request = DownloadManager.Request(uri)
    request.setDescription("Kml DL_Description").setTitle("KmL DL_Title.jpg")
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
    request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_MOVIES, "voyager.jpg")
    downloadManager.enqueue(request)
//            inputStream = InputStream(URL("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny").openStream())

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