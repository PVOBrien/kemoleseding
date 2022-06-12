package com.kml.github.kemoleseding

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.options.StorageDownloadFileOptions
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
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
    incomingFile: String
): Uri {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
    lateinit var file: File

    try {
        file =
            File("${context.getExternalFilesDir("Movies")}" + "/$incomingFile")
        println(file.toString())
        if (!file.exists()) {
            downloadVideo(context, incomingFile)
            println("THIS IS fCAUV: video doesn't exist block")
            inputStream = context.assets.open("video/$incomingFile")
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

    return file.toUri()
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

fun loginToAWS(context: Context) {
    try {
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.addPlugin(AWSS3StoragePlugin())
        Amplify.configure(context)
        Log.i("S3", "Initialized Amplify")
    } catch (error: AmplifyException) {
        Log.e("Amplify", "Amplify Error: ", error)
    }

    val aiDeet: ApplicationInfo =
        context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
    val un = aiDeet.metaData["un"] as String
    val pw = aiDeet.metaData["pw"] as String

    Amplify.Auth.signIn(un, pw,
        { result ->
            if (result.isSignInComplete) {
                Log.i("AuthQuickstart", "Sign in succeeded")
            } else {
                Log.i("AuthQuickstart", "Sign in not complete")
            }
        },
        { Log.e("AuthQuickstart", "Failed to sign in", it) }
    )
}

private fun downloadVideo(context: Context, s3Key: String) {
    println("in downloadVideo, key: $s3Key")

    Amplify.Storage.getUrl(
        s3Key,
        { Log.i("S3", "Successfully generated: ${it.url}") },
        { Log.e("S3", "URL generation failure", it) }
    )

// =************************************=

// TODO: https://github.com/awslabs/aws-sdk-android-samples/blob/main/S3TransferUtilitySample/src/com/amazonaws/demo/s3transferutility/DownloadActivity.java for using S3 TransferUtility, instead of logging in. :)
//
//    val aFile = File("${context.getExternalFilesDir("Movies")}/modOneSubSETS.mp4")
//    val region = Region.getRegion(Regions.US_WEST_2)
//    val credentials = BasicAWSCredentials("---", "----") // yes, these credentials are dead.
//    val staticCredentialsProvider = StaticCredentialsProvider(credentials)
//    val options = StorageDownloadFileOptions.builder()......

// =************************************=

    val aFile = File("${context.getExternalFilesDir("Movies")}/$s3Key")
    val options = StorageDownloadFileOptions.defaultInstance()
    Amplify.Storage.downloadFile(s3Key, aFile, options,
        { Log.i("S3.download", "% Download: ${it.fractionCompleted}") },
        { Log.i("S3.download", "Successfully downloaded: ${it.file.name}") },
        { Log.e("S3.download", "Download Failure", it) }
    )
}
