package dev.greenox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import dev.greenox.data.Dogs
import dev.greenox.model.Dog
import dev.greenox.ui.theme.WoofAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WoofAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@Composable
fun WoofApp() {
    Scaffold(
        topBar = {
            WoofTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(items = Dogs().dogs) {
                DogItem(
                    dog = it,
                    modifier = Modifier
                        .padding(all = dimensionResource(id = R.dimen.padding_8))
                )
            }
        }
    }
}

@Composable
fun DogItem(
    dog: Dog,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(value = false) }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = dimensionResource(id = R.dimen.padding_8))
            ) {
                DogIcon(dogIcon = dog.image)
                DogInformation(dogName = dog.name, dogAge = dog.age)
                Spacer(Modifier.weight(weight = 1f))
                DogItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                )
            }
            if (expanded) {
                DogHobby(
                    dogHobby = dog.hobbies,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_16),
                        top = dimensionResource(id = R.dimen.padding_8),
                        bottom = dimensionResource(id = R.dimen.padding_16),
                        end = dimensionResource(id = R.dimen.padding_16)
                    )
                )
            }
        }
    }
}

@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector =
                if (expanded)
                    Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(size = dimensionResource(id = R.dimen.image_size))
                        .padding(all = dimensionResource(id = R.dimen.padding_8)),
                    painter = painterResource(id = R.drawable.ic_woof_logo),
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
    )
}

@Composable
fun DogIcon(
    @DrawableRes dogIcon: Int,
) {
    Image(
        modifier = Modifier
            .size(size = dimensionResource(id = R.dimen.image_size))
            .padding(all = dimensionResource(id = R.dimen.padding_8))
            .clip(shape = MaterialTheme.shapes.extraLarge),
        contentScale = ContentScale.Crop,
        painter = painterResource(id = dogIcon),
        contentDescription = null
    )
}

@Composable
fun DogInformation(
    @StringRes dogName: Int,
    dogAge: Int,
) {
    Column(
        modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_8))
    ) {
        Text(
            text = stringResource(id = dogName),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.years_old, dogAge),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.padding_4))
        )
    }
}

@Composable
fun DogHobby(
    @StringRes dogHobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.about),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(id = dogHobby),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_4)
            )
        )
    }
}

@Preview
@Composable
fun WoofPreview() {
    WoofAppTheme(darkTheme = false) {
        WoofApp()
    }
}

@Preview
@Composable
fun WoofDarkThemePreview() {
    WoofAppTheme(darkTheme = true) {
        WoofApp()
    }
}