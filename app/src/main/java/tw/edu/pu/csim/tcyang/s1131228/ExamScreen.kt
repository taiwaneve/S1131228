package tw.edu.pu.csim.tcyang.s1131228

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExamScreen() {
    // 取得螢幕寬高（px）
    val metrics = LocalContext.current.resources.displayMetrics
    val screenWidthPx = metrics.widthPixels
    val screenHeightPx = metrics.heightPixels

    Log.d("ExamScreen", "螢幕寬度: $screenWidthPx px, 高度: $screenHeightPx px")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFF00)) // 黃色背景
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = "快樂圖示",
                modifier = Modifier
                    .size(200.dp) // 可依需求調整大小
            )

            Spacer(modifier = Modifier.height(10.dp))

            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "瑪利亞基金會服務大考驗", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "作者：資管二B 楊博薰", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "螢幕大小：${screenWidthPx.toFloat()} * ${screenHeightPx.toFloat()}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "成績：0分", fontSize = 16.sp)
        }
    }
}