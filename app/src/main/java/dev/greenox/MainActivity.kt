package dev.greenox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.greenox.ui.theme.LimonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LimonadeAppTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lemonade(
    modifier: Modifier = Modifier
) {
    var lemonade by remember { mutableIntStateOf(value = 1) }
    var juice by remember { mutableIntStateOf(value = 1) }

    val image = when (lemonade) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val info = when (lemonade) {
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.lemonade),
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Yellow
                )
            )
        }
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Button(
                shape = ShapeDefaults.ExtraLarge,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.mint_whisper)
                ),
                modifier = Modifier
                    .padding(paddingValues = padding),
                onClick = {
                    if (lemonade == 2) {
                        if (juice % 3 == 0) {
                            lemonade++
                            juice = 1
                        } else {
                            juice = (1..10).random()
                        }
                    } else if (lemonade == 4) {
                        lemonade = 1
                    } else {
                        lemonade++
                    }
                }
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = lemonade.toString(),
                )
            }
            Spacer(
                modifier = Modifier.height(height = 16.dp)
            )
            Text(
                text = stringResource(id = info),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Lemonade app"
)
@Composable
fun LemonadeApp() {
    Lemonade(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    )
}