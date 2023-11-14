package com.olefaent.kanyeapp.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.olefaent.kanyeapp.R
import com.olefaent.kanyeapp.model.Kanye

@Composable
fun KanyeScreen(
    uiState: KanyeState,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
){

    when (uiState) {
        is KanyeState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is KanyeState.Success -> KanyeQuoteScreen(kanye= uiState.kanye, modifier= modifier, retryAction = retryAction)
        is KanyeState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }

//    Box(
//        modifier = modifier.fillMaxSize(),
//    )
//    {
//        Image(
//            modifier = Modifier.align(Alignment.Center)
//                .fillMaxSize(),
//            painter = painterResource(id = R.drawable.kanye),
//            contentDescription = "Kanye's Image",
//            contentScale = ContentScale.Crop
//        )
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    Brush.verticalGradient(
//                    colors = listOf(Color.Transparent, Color.Black),
//                    startY = 0.5f
//                ))
//        )
//
//        Text(
//            text = "\"We've gotten comfortable with not having what we deserve\" - Kanye West",
//            color = Color.White,
//            modifier = Modifier.align(Alignment.BottomCenter)
//                .padding(32.dp),
//            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = Playfair),
//       )
//    }

}

@Composable
fun KanyeQuoteScreen(
    kanye: Kanye,
    modifier: Modifier = Modifier,
    retryAction: () -> Unit
) {

    val Playfair = FontFamily(
        Font(
            R.font.playfairdesplay,
            FontWeight.Bold
        )
    )
    val randomImage = randomImages()



    Box(
        modifier = modifier.fillMaxSize(),
    )
    {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            painter = painterResource(id = randomImage),
            contentDescription = "Kanye's Image",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0.5f
                    )
                )
        )


        Text(
            text = "\"${kanye.quote}\" \n - Kanye West",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    start=16.dp,
                    end=16.dp,
                    bottom=64.dp
                ),
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = Playfair),
        )
        FloatingActionButton(
            onClick = retryAction,
            modifier = Modifier.align(Alignment.BottomEnd)
                .padding(32.dp),
            elevation = FloatingActionButtonDefaults.elevation(8.dp),
        ) {
            Text(
                text = "New Quote",
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start=8.dp, end=8.dp, top=2.dp, bottom=2.dp)
            )
        }
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Text(text = "Loading...")
}


@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
    )
    {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.kanye2),
            contentDescription = "Kanye's Image",
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0.5f
                    )
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Text(text = "Error loading quote")
            Button(
                onClick = retryAction,
            ) {
                Icon(
                    Icons.Filled.Refresh, "Floating action button.",
                )

            }
        }
    }
}

fun randomImages(): Int {
//    create list of images from drawables and return a single random image
    val images = listOf(
        R.drawable.kanye,
        R.drawable.kanye2,
        R.drawable.kanye3,
        R.drawable.kanye4,
        R.drawable.kanye5,
        R.drawable.kanye6,
        R.drawable.kanye7,
        R.drawable.kanye8,
        R.drawable.kanye9,
        R.drawable.kanye10,
    )

    val randomImage = images.random()

    return randomImage
}


@Preview(showBackground = true)
@Composable
fun KanyeScreenPreview() {
//    KanyeScreen()
}