package dev.greenox.ui.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.greenox.R
import dev.greenox.model.AmphibiansModel

@Composable
fun AmphibiansScreen(
    getData: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
    amphibiansUiState: AmphibiansUiState,
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(
            modifier = modifier.fillMaxSize(),
        )

        is AmphibiansUiState.Success -> HomeScreen(
            amphibians = amphibiansUiState.amphibians,
            paddingValues,
            modifier = modifier.fillMaxSize(),
        )

        is AmphibiansUiState.Error -> ErrorScreen(
            getData,
            modifier = modifier.fillMaxSize(),
        )
    }
}

@Composable
fun HomeScreen(
    amphibians: List<AmphibiansModel>,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Log.d("HomeScreen", "Length: ${amphibians.size}")

    LazyColumn(
        contentPadding = paddingValues,
    ) {
        items(items = amphibians) { amphibian ->
            AmphibianUi(
                amphibian,
                modifier = modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_16),
                    vertical = dimensionResource(id = R.dimen.padding_8),
                )
            )
        }
    }
}

@Composable
fun AmphibianUi(
    amphibian: AmphibiansModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .clip(shape = MaterialTheme.shapes.medium)
    ) {
        Text(
            text = amphibian.name + " (" + amphibian.type + ")",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_6)),
        )
        AsyncImage(
            model = ImageRequest
                .Builder(context = LocalContext.current)
                .data(data = amphibian.imgUrl)
                .crossfade(enable = true)
                .build(),
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.amphibian),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(vertical = dimensionResource(id = R.dimen.padding_8))
                .fillMaxWidth()
                .wrapContentHeight(),
        )
        Text(
            text = amphibian.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.padding_6)),
        )
    }
}

