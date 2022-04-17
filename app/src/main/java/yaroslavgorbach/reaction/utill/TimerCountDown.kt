package yaroslavgorbach.reaction.utill

import android.os.CountDownTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class TimerCountDown(private val coroutineScope: CoroutineScope, private val millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval) {

    companion object {
        const val TEN_MINUTES = 600000L
        const val ONE_MINUTE = 60000L
        const val ONE_SECOND = 1000L
        const val FIFE_SECONDS = 5000L
    }

    sealed class TimerState {
        class Tick(val millisUntilFinished: Long, val timeUtilFinishedString: String, val timeUntilFinishedProgress: Float) : TimerState()
        object Finish : TimerState()
    }

    private val _state: MutableSharedFlow<TimerState> = MutableSharedFlow()

    val state: SharedFlow<TimerState>
        get() = _state

    override fun onTick(millisUntilFinished: Long) {
        coroutineScope.launch {
            val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
            val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
            val progress: Float = millisUntilFinished.toFloat() / millisInFuture.toFloat()

            _state.emit(TimerState.Tick(millisUntilFinished, "$minutes:$seconds", progress))
        }
    }

    override fun onFinish() {
        coroutineScope.launch {
            _state.emit(TimerState.Finish)
        }
    }
}