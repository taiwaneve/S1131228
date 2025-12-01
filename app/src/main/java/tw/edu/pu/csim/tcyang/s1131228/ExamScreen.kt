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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tw.edu.pu.csim.tcyang.s1131228.R

@Composable
fun ExamScreen() {
    // 取得螢幕寬高（px）
    val metrics = LocalContext.current.resources.displayMetrics
    val screenWidthPx = metrics.widthPixels
    val screenHeightPx = metrics.heightPixels
    val density = metrics.density
    val offsetYHalf = (screenHeightPx / 2 / density).dp
    val offsetYBottom = ((screenHeightPx - 300) / density).dp
    val offsetXRight = ((screenWidthPx - 300) / density).dp

    Log.d("ExamScreen", "螢幕寬度: $screenWidthPx px, 高度: $screenHeightPx px")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFF00)) // 黃色背景
    ) {
        val density = metrics.density
        val imageSizeDp = (300 / density).dp
        val offsetYHalf = (screenHeightPx / 2 / density).dp
        val offsetYBottom = ((screenHeightPx - 300) / density).dp
        val offsetXRight = ((screenWidthPx - 300) / density).dp
        val offsetYHigher = ((screenHeightPx / 2 - 300) / density).dp


        Image(
            painter = painterResource(id = R.drawable.role0),
            contentDescription = "嬰幼兒",
            modifier = Modifier
                .size(imageSizeDp)
                .absoluteOffset(x = 0.dp, y = offsetYHigher)
        )
        Image(
            painter = painterResource(id = R.drawable.role1),
            contentDescription = "兒童",
            modifier = Modifier
                .size(imageSizeDp)
                .absoluteOffset(x = offsetXRight, y = offsetYHigher)
        )
        Image(
            painter = painterResource(id = R.drawable.role2),
            contentDescription = "成人",
            modifier = Modifier
                .size(imageSizeDp)
                .absoluteOffset(x = 0.dp, y = offsetYBottom)
        )
        Image(
            painter = painterResource(id = R.drawable.role3),
            contentDescription = "一般民眾",
            modifier = Modifier
                .size(imageSizeDp)
                .absoluteOffset(x = offsetXRight, y = offsetYBottom)
        )
        // 中央文字區塊
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
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "瑪利亞基金會服務大考驗",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "作者：資管二B 楊博薰", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "螢幕大小：${screenWidthPx.toFloat()} * ${screenHeightPx.toFloat()}",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "成績：0分", fontSize = 16.sp)
        }
    }
}