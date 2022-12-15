package it.spaarkly.jetnoteapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteInputText(modifier: Modifier = Modifier,
                  text : String,
                  label : String,
                  maxLine : Int = 1,
                  onTextChange : (String) -> Unit,
                  onImeAction : () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(text = label)},
        maxLines = maxLine,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
fun NoteInputField(
    modifier: Modifier = Modifier,
    value: String,
    labelId: String,
    enabled: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    onChange : (String)-> Unit,
) {
    OutlinedTextField(
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp).fillMaxWidth(),
        value = value,
        onValueChange = {
            onChange.invoke(it)
        },
        label = { Text(text = labelId)},
        leadingIcon = { Icon(imageVector = Icons.Rounded.Edit, contentDescription = "Input Note Field") },
        singleLine = isSingleLine,
        enabled = enabled,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick : () -> Unit,
    enabled : Boolean = true
) {
    Button(onClick = onClick, 
        shape = CircleShape, 
        enabled = enabled,
    modifier = modifier) {
        Text(text = text)
    }
}