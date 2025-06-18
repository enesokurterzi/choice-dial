package com.enesokurterzi.choicedial.steppicker.horizontal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
fun <T> StepPickerHorizontal(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedIndex: Int = 0,
    onValueChange: (newIndex: Int, newItem: T) -> Unit,
    textStyle: TextStyle = TextStyle(fontSize = 20.sp),
    leftIcon: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
    rightIcon: ImageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
    colors: StepPickerHorizontalColors = StepPickerHorizontalDefaults.colors(),
    paddings: StepPickerHorizontalPaddings = StepPickerHorizontalDefaults.paddings(),
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
) {
    var innerSelectedIndex by rememberSaveable { mutableIntStateOf(selectedIndex) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(colors.backgroundColor, shape)
            .padding(paddings.contentPadding)
    ) {
        IconButton(
            modifier = Modifier.padding(paddings.leftIconPadding),
            onClick = {
                if (innerSelectedIndex > 0) {
                    innerSelectedIndex--
                    onValueChange(innerSelectedIndex, items[innerSelectedIndex])
                }
            },
            enabled = innerSelectedIndex > 0
        ) {
            Icon(
                imageVector = leftIcon,
                contentDescription = "Left Icon",
                tint = if (innerSelectedIndex > 0) colors.leftIconColor else colors.leftIconDisabledColor
            )
        }

        Text(
            modifier = Modifier.padding(paddings.textPadding),
            text = items[innerSelectedIndex].toString(),
            style = textStyle.copy(color = colors.textColor)
        )

        IconButton(
            modifier = Modifier.padding(paddings.rightIconPadding),
            onClick = {
                if (innerSelectedIndex < items.lastIndex) {
                    innerSelectedIndex++
                    onValueChange(innerSelectedIndex, items[innerSelectedIndex])
                }
            },
            enabled = innerSelectedIndex < items.lastIndex
        ) {
            Icon(
                imageVector = rightIcon,
                contentDescription = "Right Icon",
                tint = if (innerSelectedIndex < items.lastIndex) colors.rightIconColor else colors.rightIconDisabledColor
            )
        }
    }
}

@Preview
@Composable
fun StepPickerHorizontalPreview() {

    StepPickerHorizontal(
        items = (0..10).toList(),
        onValueChange = { _, _ -> }
    )
}