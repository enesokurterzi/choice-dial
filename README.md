![choicedial](https://github.com/user-attachments/assets/03376f92-5e63-46bd-a822-96dbf05b2265)<br><br>

## Introduction

**ChoiceDial** is a 🔘 *dial-style* picker library built with **Jetpack Compose**.  
It’s designed to be:

- ⚡ **Simple** – Minimal API, quick integration.
- 🪶 **Lightweight** – No unnecessary dependencies.
- 🧩 **Customisable** – Easily tailor colors, texts, paddings, and interactions.
- 🧠 **Easy to use** – Compose-friendly API with intuitive parameters.

Whether you're building a settings screen, a time picker, or a creative dial selector, **ChoiceDial** gives you the flexibility and elegance of a modern Compose component — with almost no effort.

## Implementation

[![Maven Central](https://img.shields.io/badge/Maven_Central-v1.0.0-blue)](https://central.sonatype.com/artifact/io.github.enesokurterzi/choicedial)<br>
Add the dependency below to your **module**'s `build.gradle` file:
```gradle
dependencies {
    implementation("io.github.enesokurterzi:choicedial:1.0.0")
}
```

## Usage

You can use all of this components by just giving them a list and listening higher-order functions. You can check the bottom of this page to see the default usages(Only heights and widths changed to synchronize). I will show how to customise them at this part.

### SlidePickerVertical

You can apply different `color themes` for each instance of the component depending on your screen, state, or theme mode. This makes it highly customizable, visually flexible, and easy to integrate with your app design.

<img src="https://github.com/user-attachments/assets/fe619a66-9edf-4d45-9538-a24ece9bdb11" align="right">

```kotlin
var selectedIndex = 0
var value = 0

SlidePickerVertical(
    items = (0..10).toList(),
    selectedIndex = selectedIndex,
    onValueChange = { newIndex, newValue ->
        selectedIndex = newIndex
        value = newValue
    },
    colors = SlidePickerDefaults.colors(
        textColor = Color(0xFF000000),
        lineColor = Color(0xFF4FC3F7),
        circleColor = Color(0xFFFF8A65),
        backgroundColor = Color(0xFFE0ECF8)
    )
)
```

### WheelPickerVertical

All components in the ChoiceDial library fully support `custom content lists`, allowing you to tailor each instance to your app's unique needs.

<img src="https://github.com/user-attachments/assets/c28c73ea-84f6-44bb-be0f-583599a1afb6" align="right">


```kotlin
var selectedIndex = 0
var value = ""

WheelPickerVertical(
    items = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
    selectedIndex = selectedIndex,
    onValueChange = { newIndex, newValue ->
        selectedIndex = newIndex
        value = newValue
    },
    wheelPickerWidth = 130.dp
)
```

### StepPickerVertical

You can replace the default icons by passing your own `ImageVector` resources via the icon parameters:

<img src="https://github.com/user-attachments/assets/be4ffa4c-fd80-4080-bb2d-869120a8206d" align="right">

```kotlin
var selectedIndex = 0
var value = 0

StepPickerVertical(
    items = (0..10).toList(),
    selectedIndex = selectedIndex,
    onValueChange = { newIndex, newValue ->
        selectedIndex = newIndex
        value = newValue
    },
    upIcon = ImageVector.vectorResource(id = R.drawable.ic_up_circle),
    downIcon = ImageVector.vectorResource(id = R.drawable.ic_down_circle),
)
```

### SlidePickerHorizontal

The `SlidePickerHorizontal` and `SlidePickerVertical` components offer full control over the display of item labels.

By using the `showText` parameter, you can easily hide the text labels for a cleaner or more minimal UI.

<img src="https://github.com/user-attachments/assets/6a31d16a-efc1-4259-82e3-251df3a522d7" align="right">

```kotlin
var selectedIndex = 0
var value = 0

SlidePickerHorizontal(
    items = (0..10).toList(),
    selectedIndex = selectedIndex,
    onValueChange = { newIndex, newValue ->
        selectedIndex = newIndex
        value = newValue
    },
    showText = false,
)
```

### WheelPickerHorizontal

The `WheelPickerVertical` and `WheelPickerHorizontal` components allow you to control the number of visible items in the wheel by using the `visibleItemCount` parameter.

You can choose between the following options:

- VisibleItemCount.THREE – shows 3 items at a time
- VisibleItemCount.FIVE – shows 5 items at a time (default)
- VisibleItemCount.SEVEN – shows 7 items at a time

<img src="https://github.com/user-attachments/assets/05aaf61f-f477-4c96-952a-a9fb02f4aae6" align="right">

```kotlin
var selectedIndex = 0
var value = 0

WheelPickerHorizontal(
    items = (0..10).toList(),
    selectedIndex = selectedIndex,
    onValueChange = { newIndex, newValue ->
        selectedIndex = newIndex
        value = newValue
    },
    visibleItemCount = VisibleItemCount.THREE
)
```

### StepPickerHorizontal

All components in the `ChoiceDial` library support full customization of `text style` and `padding` to help you match your app’s design needs.

You can:

-Apply a custom TextStyle to control the font, size, weight, color, and style.
-Adjust internal spacing using PaddingValues for precise layout control.

<img src="https://github.com/user-attachments/assets/744cab8b-adb2-4f63-a24c-718633f3a155" align="right">

```kotlin
var selectedIndex = 0
var value = 0

StepPickerHorizontal(
    items = (0..10).toList(),
    selectedIndex = selectedIndex,
    onValueChange = { newIndex, newValue ->
        selectedIndex = newIndex
        value = newValue
    },
    textStyle = TextStyle(fontStyle = FontStyle.Italic, fontSize = 24.sp),
    paddings = StepPickerHorizontalDefaults.paddings(textPadding = PaddingValues(horizontal = 64.dp))
)
```

|                                                                Default Usages                                                                 |
|:----------------------------------------------------------------------------------------------------------------------------------------------:|
| <img src="https://github.com/user-attachments/assets/ccb39afc-660b-4a63-82ec-6bafa18bbfab" align="center" width="50%"/> |

