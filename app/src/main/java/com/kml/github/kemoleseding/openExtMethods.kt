package com.kml.github.kemoleseding

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.*
import java.net.URL

fun fileCreateAndUri (context: Context, incomingFile: String, itsExtension: String = "pdf"): Uri {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
    lateinit var file: File

    try {
        file =
            File("${context.getExternalFilesDir("kmlpdfFromFile")}" + "/$incomingFile.pdf")
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

fun fileCreateAndUriVideo (context: Context, incomingFile: String, itsExtension: String = "mp4"): Uri {
    var inputStream: BufferedInputStream? = null
    var outputStream: OutputStream? = null
    lateinit var file: File

    try {
        file =
            File("${context.getExternalFilesDir("kmlVideo")}" + "/bigbunny.$itsExtension")
        if (!file.exists()) {
            println("in file doesn't exist block")
            inputStream = BufferedInputStream(URL("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny").openStream())
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


fun retrieveAndOpenFile(
    theContext: Context,
    incomingFile: String,
    itsExtension: String = "pdf"
) {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null


    try {
        val file =
            File("${theContext.getExternalFilesDir("kmlpdfFromFile")}" + "/$incomingFile.pdf")
        if (!file.exists()) {
            println("in file doesn't exist block")
            inputStream = theContext.assets.open("pdfs/$incomingFile.$itsExtension")
            outputStream = FileOutputStream(file)
            copyFile(inputStream, outputStream)
        }

        val uri = FileProvider.getUriForFile(
            theContext,
            "com.kml.github.kemoleseding.provider",
            file
        )

        println(uri.toString())
        val pdfToOpen = Intent(Intent.ACTION_VIEW)
            .setDataAndType(
                uri,
                theContext.contentResolver.getType(uri)
            )
            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)

        val intent = Intent.createChooser(
            pdfToOpen,
            "Open Pdf using..."
        )
        theContext.startActivity(intent)

    } catch (ex: IOException) {
        println("IOException " + ex.message)
    } catch (ex: ActivityNotFoundException) {
        println(ex.message)
    } finally {
        inputStream?.close()
        outputStream?.flush()
        outputStream?.close()
    }
    println("File copy and/or view in action")
}

private fun copyFile(input: InputStream, output: OutputStream) {
    println("in copyfile")
    val buffer = ByteArray(1024)
    var read: Int = input.read(buffer)
    while (read != -1) {
        output.write(buffer, 0, read)
        read = input.read(buffer)
        println(buffer)
    }
}