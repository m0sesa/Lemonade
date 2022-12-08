package dev.mosesadewale.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mosesadewale.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var stage by remember { mutableStateOf(0) }
    var tapsCounter by remember { mutableStateOf(0) }
    var requiredTaps by remember { mutableStateOf(1) }

    val stageImageResource: Int
    val stageImageDescription: Int
    val stageText: Int

    when (stage){
        0 ->{
            stageImageResource = R.drawable.lemon_tree
            stageImageDescription = R.string.lemon_tree
            stageText = R.string.tree_text
        }
        1 ->{
            stageImageResource = R.drawable.lemon_squeeze
            stageImageDescription = R.string.lemon
            stageText = R.string.squeeze_lemon
        }
        2 ->{
            stageImageResource = R.drawable.lemon_drink
            stageImageDescription = R.string.glass_of_lemonade
            stageText = R.string.drink_lemon
        }
        else ->{
            stageImageResource = R.drawable.lemon_restart
            stageImageDescription = R.string.empty_glass
            stageText = R.string.start_Again
        }
    }

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize(
                Alignment.Center
            )
        ) {
            Text(
                text = stringResource(stageText),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Image(
                painter = painterResource(stageImageResource),
                contentDescription = stringResource(stageImageDescription),
                modifier = Modifier.border(
                    BorderStroke(1.dp, Color.Cyan),
                    shape = RoundedCornerShape(4.dp)
                ).clickable {
                    tapsCounter++
                    if (requiredTaps == tapsCounter){
                        stage = (stage+1) % 4

                        requiredTaps = if (stage == 1){
                            (2..4).random()
                        }else{
                            1
                        }
                        tapsCounter = 0
                    }
                }.padding(all = 16.dp)
                ,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}