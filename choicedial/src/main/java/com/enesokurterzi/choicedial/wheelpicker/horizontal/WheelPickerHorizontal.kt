package com.enesokurterzi.choicedial.wheelpicker.horizontal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.enesokurterzi.choicedial.wheelpicker.VisibleItemCount
import com.enesokurterzi.choicedial.wheelpicker.WheelPickerColors
import com.enesokurterzi.choicedial.wheelpicker.WheelPickerPaddings
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun <T> WheelPickerHorizontal(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedIndex: Int = 0,
    onValueChange: (newIndex: Int, newItem: T) -> Unit,
    visibleItemCount: VisibleItemCount = VisibleItemCount.FIVE,
    wheelPickerHeight: Dp = 48.dp,
    itemWidth: Dp = 32.dp,
    unSelectedTextStyle: TextStyle = TextStyle.Default.copy(fontSize = 18.sp),
    selectedTextStyle: TextStyle = unSelectedTextStyle.copy(fontSize = 22.sp),
    backgroundShape: Shape = RoundedCornerShape(12.dp),
    colors: WheelPickerColors = WheelPickerHorizontalDefaults.colors(),
    paddings: WheelPickerPaddings = WheelPickerHorizontalDefaults.paddings(),
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val totalItemCount = items.size

    val paddingCount = visibleItemCount.count / 2
    val paddedItems = List(paddingCount) { null } + items + List(paddingCount) { null }

    val itemWidthWithPadding = itemWidth + paddings.textPadding.calculateLeftPadding(LayoutDirection.Ltr) +
            paddings.textPadding.calculateRightPadding(LayoutDirection.Ltr)

    val selectedItemIndex by remember {
        derivedStateOf {
            val offset = listState.firstVisibleItemScrollOffset
            val needsIncrement = offset > itemWidthWithPadding.value
            listState.firstVisibleItemIndex + paddingCount + if (needsIncrement) 1 else 0
        }
    }

    LaunchedEffect(Unit) {
        listState.scrollToItem((selectedIndex).coerceIn(0, totalItemCount - 1))
    }

    LaunchedEffect(Unit) {
        var previousSelectedIndex = -1
        snapshotFlow { listState.isScrollInProgress }
            .distinctUntilChanged()
            .collectLatest { isScrolling ->
                if (!isScrolling) {
                    val targetIndex = listState.firstVisibleItemIndex +
                            if (listState.firstVisibleItemScrollOffset > itemWidthWithPadding.value) 1 else 0
                    val clampedIndex = targetIndex.coerceIn(0, totalItemCount - 1)
                    if (listState.firstVisibleItemScrollOffset > 0) {
                        coroutineScope.launch {
                            listState.animateScrollToItem(clampedIndex)
                        }
                        if (previousSelectedIndex != clampedIndex) {
                            onValueChange(clampedIndex, items[clampedIndex])
                        }
                        previousSelectedIndex = clampedIndex
                    }
                }
            }
    }

    Box(
        modifier = modifier
            .height(wheelPickerHeight)
            .background(colors.backgroundColor, shape = backgroundShape)
            .padding(paddings.contentPadding)
    ) {
        LazyRow(
            state = listState,
            modifier = Modifier
                .width((((visibleItemCount.count) * itemWidthWithPadding.value)).dp)
                .fillMaxHeight()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            itemsIndexed(paddedItems) { index, item ->
                val isSelected = index == selectedItemIndex
                Text(
                    text = item?.toString() ?: "",
                    style = if (isSelected)
                        selectedTextStyle.copy(color = colors.selectedTextColor)
                    else
                        unSelectedTextStyle.copy(color = colors.unSelectedTextColor),
                    modifier = Modifier
                        .padding(paddings.textPadding)
                        .width(itemWidth),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        listOf(
            Alignment.CenterStart to listOf(colors.backgroundColor, Color.Transparent),
            Alignment.CenterEnd to listOf(Color.Transparent, colors.backgroundColor)
        ).forEach { (alignment, gradientColors) ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(itemWidthWithPadding)
                    .align(alignment)
                    .background(Brush.horizontalGradient(gradientColors))
            )
        }
    }
}

@Composable
@Preview
fun WheelPickerHorizontalPreview() {
    WheelPickerHorizontal(
        items = (0..10).toList(),
        onValueChange = { _, _ -> }
    )
}
