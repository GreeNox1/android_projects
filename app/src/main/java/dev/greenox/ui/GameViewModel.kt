package dev.greenox.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.greenox.data.MAX_NO_OF_WORDS
import dev.greenox.data.SCORE_INCREASE
import dev.greenox.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(value = GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var currentWord: String

    var usedGuess by mutableStateOf(value = "")
        private set

    init {
        resetGame()
    }

    fun updateUserGuess(guessedWord: String) {
        usedGuess = guessedWord
    }

    fun updateGameState(updatedScore: Int) {
        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update {
                currentState -> currentState.copy(
                    isGuessedWordWrong = false,
                    score = updatedScore,
                    isGameOver = true,
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    score = updatedScore,
                    currentWordCount = currentState.currentWordCount.inc()
                )
            }
        }
    }

    fun skipWord() {
        updateGameState(updatedScore = _uiState.value.score)

        updateUserGuess(guessedWord = "")
    }

    fun checkUserGuess() {
        if (usedGuess.equals(other = currentWord, ignoreCase = true)) {
            val updatedScore = _uiState.value.score.plus(other = SCORE_INCREASE)

            updateGameState(updatedScore = updatedScore)
        } else {
            _uiState.update {
                currentState -> currentState.copy(isGuessedWordWrong = true)
            }
        }

        updateUserGuess(guessedWord = "")
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    private fun shuffleCurrentWord(word: String) : String {
        val tempWord = word.toCharArray()

        tempWord.shuffle()
        while (String(chars = tempWord) == word) {
            tempWord.shuffle()
        }
        return String(chars = tempWord)
    }

    private fun pickRandomWordAndShuffle() : String {
        currentWord = allWords.random()
        return if (usedWords.contains(currentWord)) {
            pickRandomWordAndShuffle()
        } else {
            usedWords.add(currentWord)
            shuffleCurrentWord(currentWord)
        }
    }
}