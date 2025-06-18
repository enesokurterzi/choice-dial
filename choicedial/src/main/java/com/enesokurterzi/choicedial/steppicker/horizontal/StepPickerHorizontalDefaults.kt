package com.enesokurterzi.choicedial.steppicker.horizontal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
object StepPickerHorizontalDefaults {

    @Composable
    fun colors(
        backgroundColor: Color = Color.DarkGray,
        textColor: Color = Color.White,
        leftIconColor: Color = Color.White,
        rightIconColor: Color = Color.White,
        leftIconDisabledColor: Color = leftIconColor.copy(alpha = 0.5f),
        rightIconDisabledColor: Color = rightIconColor.copy(alpha = 0.5f)
    ): StepPickerHorizontalColors {
        return StepPickerHorizontalColors(
            backgroundColor = backgroundColor,
            textColor = textColor,
            leftIconColor = leftIconColor,
            rightIconColor = rightIconColor,
            leftIconDisabledColor = leftIconDisabledColor,
            rightIconDisabledColor = rightIconDisabledColor
        )
    }

    @Composable
    fun paddings(
        contentPadding: PaddingValues = PaddingValues(8.dp),
        textPadding: PaddingValues = PaddingValues(horizontal = 8.dp),
        leftIconPadding: PaddingValues = PaddingValues(0.dp),
        downIconPadding: PaddingValues = leftIconPadding
    ): StepPickerHorizontalPaddings {
        return StepPickerHorizontalPaddings(
            contentPadding = contentPadding,
            textPadding = textPadding,
            leftIconPadding = leftIconPadding,
            rightIconPadding = downIconPadding
        )
    }
}

@Immutable
data class StepPickerHorizontalColors
constructor(
    val backgroundColor: Color,
    val textColor: Color,
    val leftIconColor: Color,
    val rightIconColor: Color,
    val leftIconDisabledColor: Color,
    val rightIconDisabledColor: Color,
)

@Immutable
data class StepPickerHorizontalPaddings
constructor(
    val contentPadding: PaddingValues,
    val textPadding: PaddingValues,
    val leftIconPadding: PaddingValues,
    val rightIconPadding: PaddingValues
)