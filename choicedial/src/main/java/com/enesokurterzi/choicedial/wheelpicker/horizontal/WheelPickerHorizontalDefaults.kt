package com.enesokurterzi.choicedial.wheelpicker.horizontal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.enesokurterzi.choicedial.wheelpicker.WheelPickerColors
import com.enesokurterzi.choicedial.wheelpicker.WheelPickerPaddings

@Immutable
object WheelPickerHorizontalDefaults {

    @Composable
    fun colors(
        backgroundColor: Color = Color.DarkGray,
        unSelectedTextColor: Color = Color.Gray,
        selectedTextColor: Color = Color.White
    ): WheelPickerColors {
        return WheelPickerColors(
            backgroundColor = backgroundColor,
            unSelectedTextColor = unSelectedTextColor,
            selectedTextColor = selectedTextColor
        )
    }

    @Composable
    fun paddings(
        contentPadding: PaddingValues = PaddingValues(8.dp),
        textPadding: PaddingValues = PaddingValues(horizontal = 6.dp)
    ): WheelPickerPaddings {
        return WheelPickerPaddings(
            contentPadding = contentPadding,
            textPadding = textPadding
        )
    }
}