package dev.greenox.ui

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.greenox.R
import dev.greenox.ui.theme.UnscrambleTheme

@Composable
fun GameScreen(
    gameViewModel: GameViewModel = viewModel()
) {
    val gameUiState by gameViewModel.uiState.collectAsState()
    val mediumPadding = dimensionResource(id = R.dimen.padding_16)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .padding(all = mediumPadding)
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = typography.titleLarge,
        )
        GameLayout(
            currentScrambledWord = gameUiState.currentScrambledWord,
            userGuess = gameViewModel.usedGuess,
            isGuessWrong = gameUiState.isGuessedWordWrong,
            wordCount = gameUiState.currentWordCount,
            onUserGuessChanged = {
                gameViewModel.updateUserGuess(guessedWord = it)
            },
            onKeyboardDone = {
                gameViewModel.checkUserGuess()
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(all = mediumPadding)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = mediumPadding),
            verticalArrangement = Arrangement.spacedBy(space = mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    gameViewModel.checkUserGuess()
                }
            ) {
                Text(
                    text = stringResource(id = R.string.submit),
                    fontSize = 16.sp
                )
            }

            OutlinedButton(
                onClick = {
                    gameViewModel.skipWord()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.skip),
                    fontSize = 16.sp
                )
            }
        }

        GameStatus(score = gameUiState.score, modifier = Modifier.padding(all = 20.dp))
    }

    if (gameUiState.isGameOver) {
        FinalScoreDialog(
            score = gameUiState.score,
            onPlayAgain = {
                gameViewModel.resetGame()
            }
        )
    }
}

@Composable
fun GameStatus(score: Int, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.score, score),
            style = typography.headlineMedium,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}

@Composable
fun GameLayout(
    currentScrambledWord: String,
    onUserGuessChanged: (String) -> Unit,
    onKeyboardDone: () -> Unit,
    userGuess: String,
    wordCount: Int,
    isGuessWrong: Boolean,
    modifier: Modifier = Modifier,
) {
    val mediumPadding = dimensionResource(id = R.dimen.padding_16)

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(all = mediumPadding)
        ) {

            Text(
                text = stringResource(id = R.string.word_count, wordCount),
                style = typography.titleMedium,
                color = colorScheme.onPrimary,
                modifier = Modifier
                    .clip(shape = shapes.medium)
                    .background(color = colorScheme.surfaceTint)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
                    .align(alignment = Alignment.End)
            )
            Text(
                text = currentScrambledWord,
                fontSize = 45.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.instructions),
                textAlign = TextAlign.Center,
                style = typography.titleMedium
            )
            OutlinedTextField(
                value = userGuess,
                singleLine = true,
                shape = shapes.large,
                modifier = Modifier.fillMaxWidth(),
                isError = isGuessWrong,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorScheme.surface,
                    unfocusedContainerColor = colorScheme.surface,
                    disabledContainerColor = colorScheme.surface,
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                onValueChange = onUserGuessChanged,
                label = {
                    if (isGuessWrong) {
                        Text(text = stringResource(id = R.string.wrong_guess))
                    } else {
                        Text(text = stringResource(id = R.string.enter_your_word))
                    }
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        onKeyboardDone()
                    }
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FinalScoreDialog(
    score: Int,
    onPlayAgain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val activity = (LocalActivity.current as Activity)

    BasicAlertDialog(
        onDismissRequest = {

        },
        properties = DialogProperties(

        ),
        modifier = modifier
            .clip(shape = shapes.medium)
            .background(color = colorScheme.secondaryContainer)
            .padding(all = dimensionResource(id = R.dimen.padding_16))
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.congratulations),
                style = typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(height = 16.dp))
            Text(text = stringResource(id = R.string.you_scored, score))
            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(weight = 1F))
                TextButton(
                    onClick = {
                        activity.finish()
                    }
                ) {
                    Text(text = stringResource(id = R.string.exit))
                }
                TextButton(onClick = onPlayAgain) {
                    Text(text = stringResource(id = R.string.play_again))
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun LightView() {
    UnscrambleTheme {
        GameScreen()
    }
}