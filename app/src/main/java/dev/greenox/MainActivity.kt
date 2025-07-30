package dev.greenox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.greenox.ui.theme.SuperheroesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperheroesAppTheme {
                Surface {
                    SuperheroesApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroesApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar()
        },
        modifier = modifier.fillMaxSize()
    ) {
        padding -> SuperheroListItem(
            modifier = Modifier.padding(paddingValues = padding)
        )
    }
}

@Preview
@Composable
fun SuperheroesPreview() {
    SuperheroesAppTheme {
        SuperheroesApp()
    }
}

@Preview
@Composable
fun SuperheroesDarkThemePreview() {
    SuperheroesAppTheme(darkTheme = true) {
        SuperheroesApp()
    }
}