package dev.greenox.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.greenox.R
import dev.greenox.ui.screen.AmphibiansScreen
import dev.greenox.ui.screen.AmphibiansViewModel
import dev.greenox.ui.theme.AmphibiansAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        topBar = {
            AmphibiansAppBar(scrollBehavior)
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val amphibiansModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
            AmphibiansScreen(
                paddingValues = innerPadding,
                getData = amphibiansModel::getAmphibiansData,
                amphibiansUiState = amphibiansModel.uiState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun AmphibiansPreview() {
    AmphibiansAppTheme {
        AmphibiansApp()
    }
}