package com.example.section15.Widgit

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val iconButtonsizeModifier = Modifier.size(10.dp)
@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: ()-> Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    background: Color = MaterialTheme.colorScheme.background,
    elevation: Dp = 4.dp
){
     Card (modifier = Modifier
         .padding(3.dp)
         .background(color = background, shape = CircleShape)
         .size(40.dp)
         .clip(CircleShape)
         .clickable { onClick.invoke() }
         .then(iconButtonsizeModifier),
          ){
             Icon(imageVector= imageVector, "Plus or Minus",tint= tint,  modifier = Modifier
                 .size(48.dp)
                 .align(Alignment.CenterHorizontally)
                 .padding(8.dp))
     }
}