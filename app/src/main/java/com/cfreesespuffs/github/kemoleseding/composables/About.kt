package com.cfreesespuffs.github.kemoleseding.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.cfreesespuffs.github.kemoleseding.MainViewModel
import com.cfreesespuffs.github.kemoleseding.Screens
import com.cfreesespuffs.github.kemoleseding.ui.theme.kmlLightBlue

@Composable
fun About(viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.TopScreens.About)
    Surface(
        color = kmlLightBlue,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CoCard()
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Thusang Bana Center",
//                style = MaterialTheme.typography.h4,
//                modifier = Modifier.padding(8.dp)
//            )
//            Image(
//                painter = painterResource(id = R.drawable.tbclogo),
//                contentDescription = "Thusang Bana Centre logo",
//                modifier = Modifier
//                    .shadow(12.dp, clip = true)
//                    .background(Color.White, RectangleShape)
//                    .size(128.dp)
//            )
//            Spacer(modifier = Modifier.padding(4.dp))
//            Text(
//                text = "     Thusang Bana Centre (TBC) is a community voluntary based nonprofit making organization in Molepolole with the mandate of caring for orphans & vulnerable children. Their health educators work to destigmatize HIV and AIDS in the Kweneng district and beyond. \n\n     TBC's founder Stanley Monageng is a tireless advocate and Treat All Champion. The film Ke mo Leseding follows Stanley Monageng as he undertakes a 400 kilometer trek throughout Botswana to raise awareness of HIV treatment, and destigmatize HIV and AIDS, and educate Batswana on the latest treatment for the disease.",
//                style = MaterialTheme.typography.body2,
//                modifier = Modifier
//                    .background(
//                        SolidColor(Color.White),
//                        alpha = .5F
//                    )
//                    .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 12.dp)
//            )
//            Divider(
//                thickness = 6.dp,
//                color = Color.Black
//            )
//        }
    }
}