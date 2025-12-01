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

data class RoleZone(
    val name: String,
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float
)

class ExamViewModel : ViewModel() {

    private val _iconState = MutableStateFlow(ServiceIconState())
    val iconState: StateFlow<ServiceIconState> = _iconState

    private val _scoreText = MutableStateFlow("成績：0分")
    val scoreText: StateFlow<String> = _scoreText

    private var score = 0
    private lateinit var roleZones: List<RoleZone>

    suspend fun startFalling(screenWidthPx: Int, screenHeightPx: Int, iconSizePx: Int) {
        val centerX = (screenWidthPx - iconSizePx) / 2f
        var y = 0f
        var resId = randomIcon()

        val roleSize = 300f
        val topY = (screenHeightPx / 2 - roleSize * 1.5f).toFloat()
        val bottomY = (screenHeightPx - roleSize).toFloat()

        roleZones = listOf(
            RoleZone("嬰幼兒", 0f, topY, roleSize, roleSize),
            RoleZone("兒童", (screenWidthPx - roleSize).toFloat(), topY, roleSize, roleSize),
            RoleZone("成人", 0f, bottomY, roleSize, roleSize),
            RoleZone("一般民眾", (screenWidthPx - roleSize).toFloat(), bottomY, roleSize, roleSize)
        )

        _iconState.update {
            it.copy(x = centerX, y = y, resId = resId)
        }

        while (true) {
            delay(100)
            y += 20f

            val iconX = _iconState.value.x
            val iconY = y
            val iconW = iconSizePx.toFloat()
            val iconH = iconSizePx.toFloat()

            val hitRole = roleZones.firstOrNull { zone ->
                iconX < zone.x + zone.width &&
                        iconX + iconW > zone.x &&
                        iconY < zone.y + zone.height &&
                        iconY + iconH > zone.y
            }

            val resultText = if (hitRole != null) {
                "（碰撞${hitRole.name}圖示）"
            } else if (iconY + iconSizePx >= screenHeightPx) {
                "（掉到最下方）"
            } else {
                ""
            }

            _scoreText.value = "成績：${score}分 $resultText"

            if (iconY + iconSizePx >= screenHeightPx) {
                score += 1
                y = 0f
                resId = randomIcon()
            }

            val newX = if (y == 0f) centerX else _iconState.value.x
            _iconState.update { it.copy(x = newX, y = y, resId = resId) }
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