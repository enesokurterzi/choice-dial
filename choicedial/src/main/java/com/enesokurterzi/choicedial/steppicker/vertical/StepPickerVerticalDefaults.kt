package com.enesokurterzi.choicedial.steppicker.vertical

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
object StepPickerVerticalDefaults {

    @Composable
    fun colors(
        backgroundColor: Color = Color.DarkGray,
        textColor: Color = Color.White,
        upIconColor: Color = Color.White,
        downIconColor: Color = Color.White,
        upIconDisabledColor: Color = upIconColor.copy(alpha = 0.5f),
        downIconDisabledColor: Color = downIconColor.copy(alpha = 0.5f)
    ): StepPickerVerticalColors {
        return StepPickerVerticalColors(
            backgroundColor = backgroundColor,
            textColor = textColor,
            upIconColor = upIconColor,
            downIconColor = downIconColor,
            upIconDisabledColor = upIconDisabledColor,
            downIconDisabledColor = downIconDisabledColor
        )
    }

    @Composable
    fun paddings(
        contentPadding: PaddingValues = PaddingValues(8.dp),
        textPadding: PaddingValues = PaddingValues(vertical = 8.dp),
        upIconPadding: PaddingValues = PaddingValues(0.dp),
        downIconPadding: PaddingValues = upIconPadding
    ): StepPickerVerticalPaddings {
        return StepPickerVerticalPaddings(
            contentPadding = contentPadding,
            textPadding = textPadding,
            upIconPadding = upIconPadding,
            downIconPadding = downIconPadding
        )
    }
}

@Immutable
data class StepPickerVerticalColors
constructor(
    val backgroundColor: Color,
    val textColor: Color,
    val upIconColor: Color,
    val downIconColor: Color,
    val upIconDisabledColor: Color,
    val downIconDisabledColor: Color,
)

@Immutable
data class StepPickerVerticalPaddings
constructor(
    val contentPadding: PaddingValues,
    val textPadding: PaddingValues,
    val upIconPadding: PaddingValues,
    val downIconPadding: PaddingValues
)