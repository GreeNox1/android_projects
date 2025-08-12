package dev.greenox.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

class RaceParticipant(
    val name: String,
    val maxProgress: Int = 100,
    val progressDelayMillis: Long = 500L,
    private val progressIncrement: Int = 1,
) {
    init {
        require(value = maxProgress > 0) { "maxProgress=$maxProgress; must be > 0" }
        require(value = progressIncrement > 0) { "progressIncrement=$progressIncrement; must be > 0" }
    }

    var currentProgress by mutableIntStateOf(value = 0)
        private set

    suspend fun run() {
        while (currentProgress < maxProgress) {
            delay(timeMillis = progressDelayMillis)
            currentProgress += progressIncrement
        }
    }

    fun reset() {
        currentProgress = 0
    }
}

val RaceParticipant.progressFactor: Float
    get() = currentProgress / maxProgress.toFloat()