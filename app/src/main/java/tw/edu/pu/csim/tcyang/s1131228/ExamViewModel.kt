package tw.edu.pu.csim.tcyang.s1131228

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class ServiceIconState(
    val x: Float = 0f,
    val y: Float = 0f,
    val resId: Int = R.drawable.service1
)

class ExamViewModel : ViewModel() {

    private val _iconState = MutableStateFlow(ServiceIconState())
    val iconState: StateFlow<ServiceIconState> = _iconState

    suspend fun startFalling(screenWidthPx: Int, screenHeightPx: Int, iconSizePx: Int) {
        val centerX = (screenWidthPx - iconSizePx) / 2f
        var y = 0f
        var resId = randomIcon()
        _iconState.update {
            it.copy(x = centerX, y = y, resId = resId)
        }

        while (true) {
            delay(100)
            y += 20f
            val newX = if (y + iconSizePx >= screenHeightPx) {
                y = 0f
                resId = randomIcon()
                centerX // ✅ 碰到底部時重設為中間
            } else {
                _iconState.value.x // ✅ 其他時間保留目前拖曳後的 x
            }

            _iconState.update {
                it.copy(x = newX, y = y, resId = resId) }
        }
    }

    fun dragHorizontally(deltaX: Float, screenWidthPx: Int, iconSizePx: Int) {
        _iconState.update {
            val newX = (it.x + deltaX).coerceIn(0f, (screenWidthPx - iconSizePx).toFloat())
            it.copy(x = newX)
        }
    }

    private fun randomIcon(): Int {
        val icons = listOf(
            R.drawable.service1,
            R.drawable.service2,
            R.drawable.service3,
            R.drawable.service0,
        )
        return icons.random()
    }
}