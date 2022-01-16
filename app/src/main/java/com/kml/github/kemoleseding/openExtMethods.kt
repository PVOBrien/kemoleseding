package com.kml.github.kemoleseding

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
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
    incomingFile: String,
    itsExtension: String = "mp4"
): Uri {
    var inputStream: InputStream? = null
    var outputStream: OutputStream? = null
    lateinit var file: File

    try {
        file =
            File("${context.getExternalFilesDir("Movies")}" + "/$incomingFile.$itsExtension")
        println(file.toString())
        if (!file.exists()) {
            downloadVideo(context, "modOneSubSETS.mp4", file)
            println("THIS IS fCAUV: video doesn't exist block")
            inputStream = context.assets.open("video/$incomingFile.$itsExtension")
            outputStream = FileOutputStream(file)
            copyFile(inputStream, outputStream)

            // *** Download Manager, basic *** // // followed https://camposha.info/android-examples/android-downloadmanager/#gsc.tab=0
//            var dlId: Long = 0
//            val newBroadcastReceiver = object : BroadcastReceiver() {
//                override fun onReceive(context: Context?, intent: Intent?) {
//                    val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
//                    if (id == dlId) {
//                        Log.d("Download", "Complete")
//                    }
//                }
//            }
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
    /* OR
    return FileProvider.getUriForFile(
        context,
        "com.kml.github.kemoleseding.provider",
        file
    ) */
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
//    val emailSetup = AuthSignUpOptions.builder()
//        .userAttribute(AuthUserAttributeKey.email(), "mxpxp86@hotmail.com")
//        .build()
//    Amplify.Auth.signUp("username", "Password123", emailSetup,
//        { Log.i("AuthQuickStart", "Sign up succeeded: $it") },
//        { Log.e ("AuthQuickStart", "Sign up failed", it) }
//    )
//    Amplify.Auth.confirmSignUp(
//        "username", "554440",
//        { result ->
//            if (result.isSignUpComplete) {
//                Log.i("AuthQuickstart", "Confirm signUp succeeded")
//            } else {
//                Log.i("AuthQuickstart","Confirm sign up not complete")
//            }
//        },
//        { Log.e("AuthQuickstart", "Failed to confirm sign up", it) }
//    )

    Amplify.Auth.signIn("username", "Password123",
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

private fun downloadVideo(context: Context, s3Key: String, file: File) {
//    val uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
//        Uri.parse("https://raw.githubusercontent.com/Oclemy/SampleJSON/master/spacecrafts/voyager.jpg")
//        val uri = Uri.parse(incomingFile)
    println("in downloadVideo")

    val exampleFile = File(Environment.DIRECTORY_MOVIES, "ExampleKey.mp4")
//    exampleFile.writeText("Example file contents")

//        val optionsUpload = StorageUploadFileOptions.defaultInstance()
//        Amplify.Storage.uploadFile("ExampleKey", exampleFile, optionsUpload,
//            { Log.i("MyAmplifyApp", "Fraction completed: ${it.fractionCompleted}") },
//            { Log.i("MyAmplifyApp", "Successfully uploaded: ${it.key}") },
//            { Log.e("MyAmplifyApp", "Upload failed", it) }
//        )
//

    Amplify.Storage.getUrl(
        "modOneSubSETS.mp4",
        { Log.i("S3", "Successfully generated: ${it.url}") },
        { Log.e("S3", "URL generation failure", it) }
    )

//    val options = StorageDownloadFileOptions.defaultInstance()
//    Amplify.Storage.downloadFile("ExampleKey", file, options,
//        { Log.i("S3.mp4dl", "Fraction completed: ${it.fractionCompleted}") },
//        { Log.i("S3.mp4dl", "Successfully downloaded: ${it.file.name}") },
//        { Log.e("S3.mp4dl", "Download Failure", it) }
//    )

    val aFile = File("${context.getExternalFilesDir("Movies")}/modOneSubSETS.mp4")
    val options = StorageDownloadFileOptions.builder().accessLevel()

    Amplify.Storage.downloadFile("modOneSubSETS.mp4", aFile, options,
        { Log.i("S3.download", "% Download: ${it.fractionCompleted}") },
        { Log.i("S3.download", "Successfully downloaded: ${it.file.name}") },
        { Log.e("S3.download",  "Download Failure", it) }
    )
}

//    val request = DownloadManager.Request(uri)
//        .setDestinationInExternalFilesDir(
//            context,
//            Environment.DIRECTORY_MOVIES,
//            "bbbDest.mp4"
//        )
//        .setDescription("Kml DL_Description")
//        .setTitle("bbbTitle.mp4")
//        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
//
//    val downloadManager =
//        context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//  val uri = Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny")
//    downloadManager.enqueue(request)

//            dlId = downloadManager.enqueue(request)
////  inputStream = InputStream(URL("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny").openStream())
//
//            context.registerReceiver(
//                newBroadcastReceiver,
//                IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
//            )
//}
