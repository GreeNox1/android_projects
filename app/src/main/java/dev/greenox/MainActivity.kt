package dev.greenox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.greenox.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout() {
    val data = listOf(
        mapOf(
            "title" to "Mona Lisa",
            "artist" to "Leonardo da Vinci",
            "year" to 1503,
            "image" to R.drawable.mona_lisa
        ),
        mapOf(
            "title" to "The Starry Night",
            "artist" to "Vincent van Gogh",
            "year" to 1889,
            "image" to R.drawable.the_starry_night
        ),
        mapOf(
            "title" to "The Persistence of Memory",
            "artist" to "Salvador Dalí",
            "year" to 1931,
            "image" to R.drawable.the_persistence_of_memory
        ),
        mapOf(
            "title" to "Water Lilies",
            "artist" to "Claude Monet",
            "year" to 1906,
            "image" to R.drawable.flowers
        ),
        mapOf(
            "title" to "Girl with a Pearl Earring",
            "artist" to "Johannes Vermeer",
            "year" to 1665,
            "image" to R.drawable.girl_with_a_pearl_earring
        )
    )

    var dataIndex by remember { mutableIntStateOf(value = 0) }

    Scaffold(
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height = 80.dp)
            ) {
                Button(
                    modifier = Modifier
                        .weight(weight = 1F)
                        .padding(horizontal = 15.dp),
                    onClick = {
                        if (dataIndex != 0) {
                            dataIndex--
                        } else {
                            dataIndex = 4
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.previous))
                }

                Button(
                    modifier = Modifier
                        .weight(weight = 1F)
                        .padding(horizontal = 15.dp),
                    onClick = {
                        if (dataIndex != 4) {
                            dataIndex++
                        } else {
                            dataIndex = 0
                        }
                    }
                ) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
        },
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues)
                .verticalScroll(state = rememberScrollState())
                .safeDrawingPadding(),
        ) {
            ImageAndInformation(
                horizontalPadding = 30.dp,
                verticalPadding = 40.dp,
                elevation = 5.dp,
                modifier = Modifier.height(height = 550.dp),
                color = colorResource(id = R.color.mint_whisper),
            ) {
                Image(
                    painter = painterResource(id = data[dataIndex]["image"] as Int),
                    contentDescription = dataIndex.toString(),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(all = 20.dp)
                )
            }

            ImageAndInformation(
                horizontalPadding = 40.dp,
                verticalPadding = 30.dp,
                modifier = Modifier.fillMaxSize(),
                color = colorResource(id = R.color.lavender_mist)
            ) {
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            all = 20.dp,
                        )
                ) {
                    Text(
                        text = (data[dataIndex]["title"] ?: "Unknown").toString(),
                        fontSize = 25.sp
                    )
                    Row {
                        Text(
                            text = (data[dataIndex]["artist"] ?: "Unknown").toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = (" (${data[dataIndex]["year"]})"),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageAndInformation(
    horizontalPadding: Dp,
    verticalPadding: Dp,
    elevation: Dp = 0.dp,
    color: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            )
            .shadow(elevation = elevation)
            .background(color = color),
        content = content
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "ArtSpace app"
)
@Composable
fun ArtSpaceApp() {
    ArtSpaceLayout()
}