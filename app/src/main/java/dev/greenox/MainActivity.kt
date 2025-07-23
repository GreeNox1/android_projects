package dev.greenox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.greenox.data.Datasource
import dev.greenox.model.Courses
import dev.greenox.ui.theme.CoursesAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CourseGridItem(
    course: Courses,
    modifier: Modifier = Modifier,
) {
    Box (
        modifier = modifier
            .padding(all = 4.dp)
            .clip(shape = RoundedCornerShape(size = 16.dp))
            .background(color = colorResource(id = R.color.lavender_mist))
            .fillMaxWidth()
    ) {
        Row {
            Image(
                contentDescription = stringResource(id = course.name),
                painter = painterResource(id = course.image),
                modifier = Modifier
                    .size(size = 68.dp)
                    .aspectRatio(ratio = 1F)
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = course.name),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Text(
                        text = course.size.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
            .padding(paddingValues = WindowInsets.safeDrawing.asPaddingValues())
    ) {
        LazyVerticalGrid (
            columns = GridCells.Fixed(count = 2),

        ){
            items(
                items = Datasource().topics
            ) {
                course -> CourseGridItem(
                    course = course
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Courses app"
)
@Composable
fun CoursesPreview() {
    CoursesApp()
}