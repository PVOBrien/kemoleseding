package com.kml.github.kemoleseding

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView

class Video : ComponentActivity() {

    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            exoPlayer = remember(context) {
                ExoPlayer.Builder(context).build().apply {
                    val video = intent.extras?.get("video").toString()
                    println("This is the video intent: $video")
                    val mediaItem =
//                        MediaItem.fromUri("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4") // sanity check. DO NOT REMOVE
                        MediaItem.fromUri(Uri.parse("file:///android_asset/video/kmlteaser.mp4"))
//                        MediaItem.fromUri(Uri.parse(video))
                    this.addMediaItem(mediaItem)
                    this.addListener(object : Player.Listener {
                        override fun onIsPlayingChanged(isPlaying: Boolean) {
                            super.onIsPlayingChanged(isPlaying)
                            if (isPlaying) {
                                val positionOfVideo = (currentPosition + 1) / 1000
                                println("what is contentPos in SSS.nnn : $positionOfVideo")
                                val assetSys: Array<String> =
                                    context.assets.list("video") as Array<String>
                                println("Here are assets: " + assetSys.contentToString())
                            }
                        }
                    })
                }
            }
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                        while (exoPlayer.isPlaying) {
                            println("playing!")
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.pause()
    }

    override fun onRestart() {
        super.onRestart()
        exoPlayer.play()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Video()
}