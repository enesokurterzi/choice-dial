package com.enesokurterzi.choicedial.wheelpicker.vertical

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.resolveDefaults
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
fun <T> WheelPickerVertical(
    modifier: Modifier = Modifier,
    items: List<T>,
    selectedIndex: Int = 0,
    onValueChange: (index: Int, item: T) -> Unit,
    visibleItemCount: VisibleItemCount = VisibleItemCount.FIVE,
    wheelPickerWidth: Dp = 55.dp,
    unSelectedTextStyle: TextStyle = TextStyle.Default.copy(fontSize = 18.sp),
    selectedTextStyle: TextStyle = unSelectedTextStyle.copy(fontSize = 22.sp),
    backgroundShape: Shape = RoundedCornerShape(12.dp),
    colors: WheelPickerColors = WheelPickerVerticalDefaults.colors(),
    paddings: WheelPickerPaddings = WheelPickerVerticalDefaults.paddings(),
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val totalItemCount = items.size

    val paddingCount = visibleItemCount.count / 2
    val paddedItems = List(paddingCount) { null } + items + List(paddingCount) { null }

    val resolvedTextStyle = resolveDefaults(TextStyle.Default, LayoutDirection.Ltr)

    val unSelectedTextStyleFontSize = if (!unSelectedTextStyle.fontSize.value.isNaN()) unSelectedTextStyle.fontSize.value else resolvedTextStyle.fontSize.value
    val selectedTextStyleFontSize = if (!selectedTextStyle.fontSize.value.isNaN()) selectedTextStyle.fontSize.value else resolvedTextStyle.fontSize.value

    val itemHeight =
        paddings.textPadding.calculateTopPadding().value +
                paddings.textPadding.calculateBottomPadding().value +
                unSelectedTextStyleFontSize

    val selectedItemIndex by remember {
        derivedStateOf {
            val needsIncrement = listState.firstVisibleItemScrollOffset > itemHeight
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
                    val targetIndex =
                        (listState.firstVisibleItemIndex + if (listState.firstVisibleItemScrollOffset > itemHeight) 1 else 0)
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
            .width(wheelPickerWidth)
            .background(colors.backgroundColor, shape = backgroundShape)
            .padding(paddings.contentPadding)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.height(((visibleItemCount.count * itemHeight) +
                    paddings.contentPadding.calculateTopPadding().value +
                    paddings.contentPadding.calculateBottomPadding().value +
                    ((selectedTextStyleFontSize - unSelectedTextStyleFontSize))).dp).fillMaxWidth().align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            itemsIndexed(paddedItems) { index, item ->
                val isSelected = index == selectedItemIndex
                Text(
                    text = item?.toString() ?: "",
                    style = if (isSelected)
                        selectedTextStyle.copy(color = colors.selectedTextColor)
                    else
                        unSelectedTextStyle.copy(color = colors.unSelectedTextColor),
                    modifier = Modifier.padding(paddings.textPadding),
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        listOf(
            Alignment.TopCenter to listOf(colors.backgroundColor, Color.Transparent),
            Alignment.BottomCenter to listOf(Color.Transparent, colors.backgroundColor)
        ).forEach { (alignment, gradientColors) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight.dp)
                    .align(alignment)
                    .background(Brush.verticalGradient(gradientColors))
            )
        }
    }
}

@Composable
@Preview
fun WheelPickerPreview() {

    WheelPickerVertical(
        items = (0..10).toList(),
        onValueChange = { _, _ -> },
    )
}