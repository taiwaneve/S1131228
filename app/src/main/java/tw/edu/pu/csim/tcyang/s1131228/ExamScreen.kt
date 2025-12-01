package tw.edu.pu.csim.tcyang.s1131228

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import tw.edu.pu.csim.tcyang.s1131228.R

@Composable
fun ExamScreen(viewModel: ExamViewModel = viewModel()) {
    val context = LocalContext.current
    val metrics = context.resources.displayMetrics
    val screenWidthPx = metrics.widthPixels
    val screenHeightPx = metrics.heightPixels
    val density = metrics.density

    val imageSizeDp = (300 / density).dp
    val offsetYHigher = ((screenHeightPx / 2 - 300) / density).dp
    val offsetYBottom = ((screenHeightPx - 300) / density).dp
    val offsetXRight = ((screenWidthPx - 300) / density).dp

    val iconSizePx = 300
    val iconSizeDp = (iconSizePx / density).dp
    val iconState by viewModel.iconState.collectAsState()

    // 啟動下落邏輯
    LaunchedEffect(Unit) {
        viewModel.startFalling(screenWidthPx, screenHeightPx, iconSizePx)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFF00))
    ) {
        // 四個角色圖示
        Image(painter = painterResource(id = R.drawable.role0), contentDescription = "嬰幼兒",
            modifier = Modifier.size(imageSizeDp).absoluteOffset(x = 0.dp, y = offsetYHigher))
        Image(painter = painterResource(id = R.drawable.role1), contentDescription = "兒童",
            modifier = Modifier.size(imageSizeDp).absoluteOffset(x = offsetXRight, y = offsetYHigher))
        Image(painter = painterResource(id = R.drawable.role2), contentDescription = "成人",
            modifier = Modifier.size(imageSizeDp).absoluteOffset(x = 0.dp, y = offsetYBottom))
        Image(painter = painterResource(id = R.drawable.role3), contentDescription = "一般民眾",
            modifier = Modifier.size(imageSizeDp).absoluteOffset(x = offsetXRight, y = offsetYBottom))

        // 中央文字區塊
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.happy), contentDescription = "快樂圖示",
                modifier = Modifier.size(200.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text("瑪利亞基金會服務大考驗", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text("作者：資管二B 楊博薰", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text("螢幕大小：${screenWidthPx.toFloat()} * ${screenHeightPx.toFloat()}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text("成績：0分", fontSize = 16.sp)
        }

        // 掉落服務圖示
        Image(
            painter = painterResource(id = iconState.resId),
            contentDescription = "服務圖示",
            modifier = Modifier
                .size(iconSizeDp)
                .absoluteOffset(
                    x = (iconState.x / density).dp,
                    y = (iconState.y / density).dp
                )
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        viewModel.dragHorizontally(dragAmount.x, screenWidthPx, iconSizePx)
                    }
                }
        )
    }
}