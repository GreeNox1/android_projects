package dev.greenox

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dev.greenox.data.DataSource
import dev.greenox.ui.theme.DessertClickerAppTheme

class MainActivity : ComponentActivity() {
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DessertClickerAppTheme {
                Surface {
                    DessertClickerApp(
                        desserts = DataSource().desserts
                    )
                }
            }
        }
        Log.d(tag, "onCreate Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(tag, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy Called")
    }
}