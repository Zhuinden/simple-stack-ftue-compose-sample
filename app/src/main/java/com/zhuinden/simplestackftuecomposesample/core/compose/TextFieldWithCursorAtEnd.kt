package com.zhuinden.simplestackftuecomposesample.core.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.indicatorLine
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation

/**
 * <a href="https://material.io/components/text-fields#filled-text-field" class="external" target="_blank">Material Design filled text field</a>.
 *
 * Filled text fields have more visual emphasis than outlined text fields, making them stand out
 * when surrounded by other content and components.
 *
 * ![Filled text field image](https://developer.android.com/images/reference/androidx/compose/material/filled-text-field.png)
 *
 * If you are looking for an outlined version, see [OutlinedTextField].
 *
 * A simple single line text field looks like:
 *
 * @sample androidx.compose.material.samples.SimpleTextFieldSample
 *
 * You may provide a placeholder:
 *
 * @sample androidx.compose.material.samples.TextFieldWithPlaceholder
 *
 * You can also provide leading and trailing icons:
 *
 * @sample androidx.compose.material.samples.TextFieldWithIcons
 *
 * To handle the error input state, use [isError] parameter:
 *
 * @sample androidx.compose.material.samples.TextFieldWithErrorState
 *
 * Additionally, you may provide additional message at the bottom:
 *
 * @sample androidx.compose.material.samples.TextFieldWithHelperMessage
 *
 * Password text field example:
 *
 * @sample androidx.compose.material.samples.PasswordTextField
 *
 * Hiding a software keyboard on IME action performed:
 *
 * @sample androidx.compose.material.samples.TextFieldWithHideKeyboardOnImeAction
 *
 * If apart from input text change you also want to observe the cursor location, selection range,
 * or IME composition use the TextField overload with the [TextFieldValue] parameter instead.
 *
 * @param value the input text to be shown in the text field
 * @param onValueChange the callback that is triggered when the input service updates the text. An
 * updated text comes as a parameter of the callback
 * @param modifier a [Modifier] for this text field
 * @param enabled controls the enabled state of the [TextField]. When `false`, the text field will
 * be neither editable nor focusable, the input of the text field will not be selectable,
 * visually text field will appear in the disabled UI state
 * @param readOnly controls the editable state of the [TextField]. When `true`, the text
 * field can not be modified, however, a user can focus it and copy text from it. Read-only text
 * fields are usually used to display pre-filled forms that user can not edit
 * @param textStyle the style to be applied to the input text. The default [textStyle] uses the
 * [LocalTextStyle] defined by the theme
 * @param label the optional label to be displayed inside the text field container. The default
 * text style for internal [Text] is [Typography.caption] when the text field is in focus and
 * [Typography.subtitle1] when the text field is not in focus
 * @param placeholder the optional placeholder to be displayed when the text field is in focus and
 * the input text is empty. The default text style for internal [Text] is [Typography.subtitle1]
 * @param leadingIcon the optional leading icon to be displayed at the beginning of the text field
 * container
 * @param trailingIcon the optional trailing icon to be displayed at the end of the text field
 * container
 * @param isError indicates if the text field's current value is in error. If set to true, the
 * label, bottom indicator and trailing icon by default will be displayed in error color
 * @param visualTransformation transforms the visual representation of the input [value]
 * For example, you can use
 * [PasswordVisualTransformation][androidx.compose.ui.text.input.PasswordVisualTransformation] to
 * create a password text field. By default no visual transformation is applied
 * @param keyboardOptions software keyboard options that contains configuration such as
 * [KeyboardType] and [ImeAction].
 * @param keyboardActions when the input service emits an IME action, the corresponding callback
 * is called. Note that this IME action may be different from what you specified in
 * [KeyboardOptions.imeAction].
 * @param singleLine when set to true, this text field becomes a single horizontally scrolling
 * text field instead of wrapping onto multiple lines. The keyboard will be informed to not show
 * the return key as the [ImeAction]. Note that [maxLines] parameter will be ignored as the
 * maxLines attribute will be automatically set to 1.
 * @param maxLines the maximum height in terms of maximum number of visible lines. It is required
 * that 1 <= [minLines] <= [maxLines]. This parameter is ignored when [singleLine] is true.
 * @param minLines the minimum height in terms of minimum number of visible lines. It is required
 * that 1 <= [minLines] <= [maxLines]. This parameter is ignored when [singleLine] is true.
 * @param interactionSource the [MutableInteractionSource] representing the stream of
 * [Interaction]s for this TextField. You can create and pass in your own remembered
 * [MutableInteractionSource] if you want to observe [Interaction]s and customize the
 * appearance / behavior of this TextField in different [Interaction]s.
 * @param shape the shape of the text field's container
 * @param colors [TextFieldColors] that will be used to resolve color of the text, content
 * (including label, placeholder, leading and trailing icons, indicator line) and background for
 * this text field in different states. See [TextFieldDefaults.textFieldColors]
 */
@Composable
fun TextFieldWithCursorAtEnd(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape =
        MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
    // If color is not provided via the text style, use content color as a default
    val textColor = textStyle.color.takeOrElse {
        colors.textColor(enabled).value
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    var textFieldValueState by remember {
        mutableStateOf(
            TextFieldValue(
                text = value, selection = when {
                    value.isEmpty() -> TextRange.Zero
                    else -> TextRange(value.length, value.length)
                }
            )
        )
    }

    val textFieldValue = textFieldValueState.copy(text = value)

    SideEffect {
        if (textFieldValue.selection != textFieldValueState.selection ||
            textFieldValue.composition != textFieldValueState.composition
        ) {
            textFieldValueState = textFieldValue
        }
    }

    var lastTextValue by remember(value) { mutableStateOf(value) }

    @OptIn(ExperimentalMaterialApi::class)
    (BasicTextField(
        value = textFieldValue,
        onValueChange = { newTextFieldValueState ->
            textFieldValueState = newTextFieldValueState

            val stringChangedSinceLastInvocation = lastTextValue != newTextFieldValueState.text
            lastTextValue = newTextFieldValueState.text

            if (stringChangedSinceLastInvocation) {
                onValueChange(newTextFieldValueState.text)
            }
        },
        modifier = modifier
            .background(colors.backgroundColor(enabled).value, shape)
            .indicatorLine(enabled, isError, interactionSource, colors)
            .defaultMinSize(
                minWidth = TextFieldDefaults.MinWidth,
                minHeight = TextFieldDefaults.MinHeight
            ),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = mergedTextStyle,
        cursorBrush = SolidColor(colors.cursorColor(isError).value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        decorationBox = @Composable { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                placeholder = placeholder,
                label = label,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                singleLine = singleLine,
                enabled = enabled,
                isError = isError,
                interactionSource = interactionSource,
                colors = colors
            )
        }
    ))
}
