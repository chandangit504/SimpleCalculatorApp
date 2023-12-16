package com.example.section15

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.section15.Component.input
import com.example.section15.Util.calculateTotalPerson
import com.example.section15.Util.calculateTotalTips
import com.example.section15.Widgit.RoundButton
import com.example.section15.ui.theme.Section15Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Section15Theme {
                // A surface container using the 'background' color from the theme
                myApp {
                    Column {

                        Spacer(modifier = Modifier.height(20.dp))
                        mainContent()
                    }

                }
            }
        }
    }
}

@Composable
fun myApp(content: @Composable () -> Unit) {
    Surface(
    ) {
        content()
    }
}


@Composable
fun header(text: Double = 0.0) {
    val total = "%.2f".format(text)
    Surface(
        modifier =
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .
//        shape = RoundedCornerShape(CornerSize(18.dp)),
                // clip(shape = RoundedCornerShape(CornerSize(18.dp))),
            clip(shape = CircleShape.copy(CornerSize(20.dp))),
               color = Color.Cyan
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Total count is ", style = MaterialTheme.typography.headlineMedium)
            Text(
                text = "Rs: $total",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.ExtraBold
            )

        }

    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun mainContent() {
    BillForm() {
        Log.d("AMT", "mainContent: $it")
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(
    modifier: Modifier = Modifier,
    typedValue: (String) -> Unit = {}
) {
    val totalInput = remember {
        mutableStateOf("")
    }
    val anyTextAvailable = remember(totalInput.value) {
        totalInput.value.trim().isNotEmpty()
    }
    val spliptByState = remember {
        mutableStateOf(1)
    }
  val  slidePositionState = remember {
      mutableStateOf(0f)
  }
    val totaltipsState = remember {
        mutableStateOf(0)
    }
    val totalbillAmmount =remember {
        mutableStateOf(0)
    }
    val tipsPersentage= (slidePositionState.value * 100).toInt()
    val keyboardController = LocalSoftwareKeyboardController.current

    header(text = totalbillAmmount.value.toDouble())

    Surface(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(corner = CornerSize(6.dp))),
            border = BorderStroke(1.dp, color = Color.Black)
    )

    {

        Column() {
            input(valueState = totalInput,
                labelId = "Enter Value",
                enabled = true,
                isSingleLine = true,
                //type value
                onaction = KeyboardActions {
                    if (!anyTextAvailable)
                        typedValue(totalInput.value.trim())
                    keyboardController?.hide()
                    return@KeyboardActions
                })
            if (anyTextAvailable) {
                Row(
                    modifier = Modifier.padding(start = 10.dp),
                    horizontalArrangement = Arrangement.Start
                )
                // Button + and -
                {
                    Text(text = "Chandan", modifier = Modifier.align(Alignment.CenterVertically))
                    Spacer(modifier = Modifier.width(160.dp))
                    Row(
                        modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RoundButton(imageVector = Icons.Default.Remove, onClick = {
                            if (spliptByState.value <= 1) {
                                spliptByState.value = spliptByState.value - 1
                                totalbillAmmount.value = calculateTotalPerson(totalBill= totalInput.value.toInt(),
                                    splitBy =spliptByState.value, totalPercentage =tipsPersentage )
                            }

                        })
                        Text(
                            text = spliptByState.value.toString(), modifier = Modifier
                                .padding(start = 4.dp, end = 4.dp)
                                .align(Alignment.CenterVertically)
                        )
                        RoundButton(imageVector = Icons.Default.Add, onClick = {
                            spliptByState.value = spliptByState.value + 1
                            totalbillAmmount.value = calculateTotalPerson(totalBill= totalInput.value.toInt(),
                                splitBy =spliptByState.value, totalPercentage =tipsPersentage )
                        })

                    }
                }
                Spacer(modifier = Modifier.width(50.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 20.dp),
                         horizontalArrangement = Arrangement.Start
                )
                // Tips persentage
                {
                    Text(
                        text = "Tips", modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 4.dp)
                    )
                    Spacer(modifier = Modifier.width(250.dp))
                    Text(text = "Rs:${tipsPersentage}",
                        modifier = Modifier.align(Alignment.CenterVertically))
                }

                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp, vertical = 10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                //slider value
                {
                    Text(
                        text = "$tipsPersentage %",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .scale(1.5f)
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                //slider
                Slider(value = slidePositionState.value,
                    onValueChange ={
                        slidePositionState.value= it
                      totaltipsState.value= calculateTotalTips(totalInput.value.toInt(),tipsPersentage )
                        totalbillAmmount.value = calculateTotalPerson(totalBill= totalInput.value.toInt(),
                            splitBy =spliptByState.value, totalPercentage =tipsPersentage )
                } ,modifier = Modifier.padding(10.dp), steps = 5,
                    onValueChangeFinished = {
                        Log.d("Slider","${slidePositionState.value}")
                    })


            } else {
                Box(modifier = Modifier)
            }
        }

    }
}

@Composable
fun GreetingPreview() {
    Section15Theme {
        myApp {

        }
    }
}