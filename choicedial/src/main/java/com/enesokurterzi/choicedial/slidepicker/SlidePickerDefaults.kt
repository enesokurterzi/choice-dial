package com.enesokurterzi.choicedial.slidepicker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
object SlidePickerDefaults {

    @Composable
    fun colors(
        textColor: Color = Color.White,
        lineColor: Color = Color.LightGray,
        circleColor: Color = Color.White,
        backgroundColor: Color = Color.DarkGray
    ): SlidePickerColors {
        return SlidePickerColors(
            textColor = textColor,
            lineColor = lineColor,
            circleColor = circleColor,
            backgroundColor = backgroundColor
        )
    }

    @Composable
    fun paddings(
        contentPadding: PaddingValues = PaddingValues(8.dp),
        textToSliderSpacing: Dp = 4.dp,
        sliderInnerPadding: PaddingValues = PaddingValues(4.dp),
        sliderBottomSpacing: Dp = 4.dp
    ): SlidePickerPaddings {
        return SlidePickerPaddings(
            contentPadding = contentPadding,
            textToSliderSpacing = textToSliderSpacing,
            sliderInnerPadding = sliderInnerPadding,
            sliderBottomSpacing = sliderBottomSpacing
        )
    }
}

@Immutable
data class SlidePickerColors
constructor(
    val textColor: Color,
    val lineColor: Color,
    val circleColor: Color,
    val backgroundColor: Color,
)

@Immutable
data class SlidePickerPaddings
constructor(
    val contentPadding: PaddingValues,
    val textToSliderSpacing: Dp,
    val sliderInnerPadding: PaddingValues,
    val sliderBottomSpacing: Dp,
)