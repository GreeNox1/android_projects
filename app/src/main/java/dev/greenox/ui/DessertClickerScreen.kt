package dev.greenox.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.greenox.R
import dev.greenox.ui.theme.DessertClickerAppTheme

@Composable
fun DessertClickerApp(
    dessertClickerViewModel: DessertClickerViewModel = viewModel(),
) {
    val dessertClickerUiState by dessertClickerViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            val intentContext = LocalContext.current
            DessertClickerAppBar(
                onShareButtonClicked = {
                    dessertClickerViewModel.shareSoldDessertsInformation(
                        intentContext = intentContext,
                        dessertsSold = dessertClickerUiState.dessertsSold,
                        revenue = dessertClickerUiState.revenue,
                    )
                },
            )
        }
    ) { contentPadding ->
        DessertClickerScreen(
            revenue = dessertClickerUiState.revenue,
            dessertsSold = dessertClickerUiState.dessertsSold,
            dessertImageId = dessertClickerUiState.currentDessertImageId,
            onDessertClicked = dessertClickerViewModel::onDessertClicked,
            modifier = Modifier.padding(paddingValues = contentPadding),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DessertClickerAppBar(
    onShareButtonClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_16)),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        actions = {
            IconButton(
                onClick = onShareButtonClicked,
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_16)),
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = stringResource(id = R.string.share),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun DessertClickerScreen(
    revenue: Int,
    dessertsSold: Int,
    @DrawableRes dessertImageId: Int,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.bakery_back),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column {
            Box(
                modifier = Modifier
                    .weight(weight = 1f)
                    .fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(dessertImageId),
                    contentDescription = null,
                    modifier = Modifier
                        .width(width = dimensionResource(id = R.dimen.image_size))
                        .height(height = dimensionResource(id = R.dimen.image_size))
                        .align(Alignment.Center)
                        .clickable { onDessertClicked() },
                    contentScale = ContentScale.Crop,
                )
            }
            TransactionInfo(
                revenue = revenue,
                dessertsSold = dessertsSold,
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            )
        }
    }
}

@Composable
private fun TransactionInfo(
    revenue: Int,
    dessertsSold: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_16)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.dessert_sold),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = dessertsSold.toString(),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = dimensionResource(id = R.dimen.padding_16)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = R.string.total_revenue),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = "$${revenue}",
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Preview(
    showSystemUi = true
)
@Composable
fun MyDessertClickerAppPreview() {
    DessertClickerAppTheme {
        DessertClickerApp()
    }
}