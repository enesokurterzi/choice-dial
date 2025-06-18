package com.enesokurterzi.choicedial.steppicker.vertical

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun <T> StepPickerVertical(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedIndex: Int = 0,
    onValueChange: (newIndex: Int, newItem: T) -> Unit,
    textStyle: TextStyle = TextStyle(fontSize = 20.sp),
    upIcon: ImageVector = Icons.Default.KeyboardArrowUp,
    downIcon: ImageVector = Icons.Default.KeyboardArrowDown,
    colors: StepPickerVerticalColors = StepPickerVerticalDefaults.colors(),
    paddings: StepPickerVerticalPaddings = StepPickerVerticalDefaults.paddings(),
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
) {
    var innerSelectedIndex by rememberSaveable { mutableIntStateOf(selectedIndex) }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(colors.backgroundColor, shape)
            .padding(paddings.contentPadding)
    ) {
        IconButton(
            modifier = Modifier.padding(paddings.upIconPadding),
            onClick = {
                if (innerSelectedIndex > 0) {
                    innerSelectedIndex -= 1
                    onValueChange(innerSelectedIndex, items[innerSelectedIndex])
                }
            },
            enabled = innerSelectedIndex > 0
        ) {
            Icon(
                imageVector = upIcon,
                contentDescription = "Up Icon",
                tint = if (innerSelectedIndex > 0) {
                    colors.upIconColor
                } else {
                    colors.upIconDisabledColor
                }
            )
        }

        Text(
            modifier = Modifier.padding(paddings.textPadding),
            text = items[innerSelectedIndex].toString(),
            style = textStyle.copy(color = colors.textColor)
        )

        IconButton(
            modifier = Modifier.padding(paddings.downIconPadding),
            onClick = {
                if (innerSelectedIndex < items.lastIndex) {
                    innerSelectedIndex += 1
                    onValueChange(innerSelectedIndex, items[innerSelectedIndex])
                }
            },
            enabled = innerSelectedIndex < items.lastIndex
        ) {
            Icon(
                imageVector = downIcon,
                contentDescription = "Down Icon",
                tint = if (innerSelectedIndex < items.lastIndex) {
                    colors.downIconColor
                } else {
                    colors.downIconDisabledColor
                }
            )
        }
    }
}

@Preview
@Composable
fun StepPickerVerticalPreview() {
    StepPickerVertical(
        items = (0..10).toList(),
        onValueChange = { _, _ -> }
    )
}