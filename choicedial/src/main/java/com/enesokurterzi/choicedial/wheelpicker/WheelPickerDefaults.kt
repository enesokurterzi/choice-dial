package com.enesokurterzi.choicedial.wheelpicker

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class WheelPickerColors
constructor(
    val backgroundColor: Color,
    val unSelectedTextColor: Color,
    val selectedTextColor: Color,
)

@Immutable
data class WheelPickerPaddings
constructor(
    val contentPadding: PaddingValues,
    val textPadding: PaddingValues,
)

enum class VisibleItemCount(val count: Int) {
    THREE(3),
    FIVE(5),
    SEVEN(7)
}