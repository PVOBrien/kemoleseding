package com.kml.github.kemoleseding.composables

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.kml.github.kemoleseding.MainViewModel
import com.kml.github.kemoleseding.Screens
import com.kml.github.kemoleseding.R
import com.kml.github.kemoleseding.ui.theme.kmlLightBlue
import com.kml.github.kemoleseding.ui.theme.kmlRed
import com.kml.github.kemoleseding.ui.theme.kmlYellow

@Composable
fun Curriculum(viewModel: MainViewModel) {

    val context = LocalContext.current
    viewModel.setCurrentScreen(Screens.TopScreens.Curriculum)

    Surface(
        color = Color.LightGray,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(top = 40.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("Ke Mo Leseding")
                    }
                    append(" is a full HIV and AIDS awareness package, complete with training materials, videos, and a facilitator guide.\n\nReach out to Thusang Bana Center for information to receive additional information.")
                },
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 24.dp)
            )
            Row(
                modifier = Modifier.padding(top = 28.dp, bottom = 36.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.kmlcurriculum),
                    contentDescription = "Ke mo Leseding Curriculum",
                    modifier = Modifier
                        .size(196.dp)
                )
                Image(
                    painterResource(id = R.drawable.kmlleaderguide),
                    contentDescription = "KmL Leader's Guide",
                    modifier = Modifier
                        .size(196.dp)
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = kmlYellow,
                    contentColor = Color.Black
                ),
                onClick = {
                    println("hello email!")
                    val intent = Intent(Intent.ACTION_SENDTO)
                    intent.data = Uri.parse("mailto:")
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("thusangbanacenter@gmail.com"))
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Ke mo Leseding Content Request")
                    ContextCompat.startActivity(
                        context,
                        Intent.createChooser(intent, "Send Email..."),
                        null
                    )
                }
            ) {
                Text(text = "Email Thusang Bana Center")
            }
        }
    }
}
