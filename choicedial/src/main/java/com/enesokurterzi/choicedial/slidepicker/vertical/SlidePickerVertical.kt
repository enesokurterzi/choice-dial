package com.enesokurterzi.choicedial.slidepicker.vertical

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesokurterzi.choicedial.slidepicker.SlidePickerColors
import com.enesokurterzi.choicedial.slidepicker.SlidePickerDefaults
import com.enesokurterzi.choicedial.slidepicker.SlidePickerPaddings

@Composable
fun <T> SlidePickerVertical(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedIndex: Int = 0,
    onValueChange: (newPosition: Int, newValue: T) -> Unit,
    sliderHeight: Dp = 150.dp,
    sliderWidth: Dp = 40.dp,
    showText: Boolean = true,
    colors: SlidePickerColors = SlidePickerDefaults.colors(),
    paddings: SlidePickerPaddings = SlidePickerDefaults.paddings(),
    shape: Shape = RoundedCornerShape(12.dp),
    textStyle: TextStyle = TextStyle.Default.copy(fontSize = 14.sp)
) {
    var sliderPosition by rememberSaveable {
        mutableFloatStateOf(
            (selectedIndex.toFloat() / (items.lastIndex)).coerceIn(0f, 1f)
        )
    }
    var localPosition by rememberSaveable { mutableIntStateOf(selectedIndex) }
    val density = LocalDensity.current

    Column(
        modifier = modifier
            .background(colors.backgroundColor, shape)
            .padding(paddings.contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showText) {
            Text(
                text = "${items[localPosition]}",
                color = colors.textColor,
                style = textStyle
            )
        }

        Spacer(Modifier.height(paddings.textToSliderSpacing))

        Box(
            modifier = Modifier
                .size(sliderWidth, sliderHeight)
                .pointerInput(Unit) {
                    detectVerticalDragGestures { _, dragAmount ->
                        val newPosition =
                            (sliderPosition - dragAmount / with(density) { sliderHeight.toPx() })
                                .coerceIn(0f, 1f)
                        sliderPosition = newPosition
                        val newValue = newPosition * (items.lastIndex)

                        if (newValue.toInt() != localPosition) {
                            onValueChange(newValue.toInt(), items[newValue.toInt()])
                            localPosition = newValue.toInt()
                        }

                    }
                }
                .padding(paddings.sliderInnerPadding),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawLine(
                    color = colors.lineColor,
                    start = Offset(size.width / 2, 0f),
                    end = Offset(size.width / 2, size.height),
                    strokeWidth = 6f
                )

                drawCircle(
                    color = colors.circleColor,
                    radius = 12f,
                    center = Offset(size.width / 2, size.height * (1f - sliderPosition))
                )
            }
        }
        Spacer(Modifier.height(paddings.sliderBottomSpacing))
    }
}

@Composable
@Preview
fun SliderPreview() {
    SlidePickerVertical(
        onValueChange = { _, _ -> },
        items = (0..10).toList()
    )
}


