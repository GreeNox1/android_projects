package dev.greenox

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.greenox.data.WellnessRepository
import dev.greenox.model.Wellness
import dev.greenox.ui.theme.WellnessAppTheme

@Composable
fun WellnessApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            WellnessAppTopBar()
        },
        modifier = modifier
    ) { paddingValues ->
        WellnessAppBody(
            paddingValues = paddingValues
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WellnessAppTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_title),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    )
}

@Composable
fun WellnessAppBody(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        itemsIndexed(
            items = WellnessRepository().wellness
        ) {
            index, wellness -> DayOfWellness(
                wellness = wellness,
                dayNumber = index + 1,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_16),
                    vertical = dimensionResource(id = R.dimen.padding_8)
                )
            )
        }
    }
}

@Composable
fun DayOfWellness(
    wellness: Wellness,
    dayNumber: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(value = false) }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .clip(
                    shape = MaterialTheme.shapes.medium
                )
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(all = dimensionResource(id = R.dimen.padding_16))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        expanded = !expanded
                    },
                )
                .animateContentSize()
                .fillMaxSize()
        ) {
            Text(
                text = context.getString(R.string.day_number, dayNumber),
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = stringResource(id = wellness.name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding_4))
            )
            Image(
                painter = painterResource(id = wellness.image),
                contentDescription = stringResource(id = wellness.name),
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.padding_4))
            )
            if (expanded) {
                Text(
                    text = stringResource(id = wellness.description),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Preview
@Composable
fun WellnessPreview() {
    WellnessAppTheme {
        WellnessApp()
    }
}

@Preview
@Composable
fun WellnessDarkThemePreview() {
    WellnessAppTheme(darkTheme = true) {
        WellnessApp()
    }
}