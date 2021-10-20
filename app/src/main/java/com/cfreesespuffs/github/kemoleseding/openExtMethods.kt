package com.cfreesespuffs.github.kemoleseding

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.*

fun openFile(
    theContext: Context,
    theFile: String
) {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null

    try {
        val file =
            File("${theContext.getExternalFilesDir("kmlpdfFromFile")}" + "/$theFile.pdf")
        if (!file.exists()) {
            println("in file doesn't exist block")
            inputStream = theContext.assets.open("pdfs/$theFile.pdf")
            outputStream = FileOutputStream(file)
            copyFile(inputStream, outputStream)
        }

        val uri = FileProvider.getUriForFile(
            theContext,
            "com.cfreesespuffs.github.kemoleseding.provider",
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