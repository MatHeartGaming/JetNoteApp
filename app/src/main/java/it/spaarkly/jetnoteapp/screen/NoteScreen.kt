package it.spaarkly.jetnoteapp.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import it.spaarkly.jetnoteapp.R
import it.spaarkly.jetnoteapp.components.NoteButton
import it.spaarkly.jetnoteapp.components.NoteInputField
import it.spaarkly.jetnoteapp.components.NoteInputText
import it.spaarkly.jetnoteapp.data.NotesDataSource
import it.spaarkly.jetnoteapp.model.Note
import it.spaarkly.jetnoteapp.util.formatDate
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen(
    notes : List<Note>,
    onAddNote : (Note) -> Unit,
    onRemoveNote : (Note) -> Unit,
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column() {
        TopAppBar(title = {
            Text(stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
        },
        backgroundColor = MaterialTheme.colors.background
        )

        //Content
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
            NoteInputField(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                value = title,
                labelId = "Title",
                onChange = {
                if(it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) title = it
            })
            NoteInputField(modifier = Modifier.padding(top = 9.dp, bottom = 8.dp),
                value = description,
                labelId = "Add a note",
                onChange = {
                    if(it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                })
            NoteButton(text = "Save", onClick = {
                if(title.isNotBlank() && description.isNotBlank()) {
                    onAddNote(Note(title = title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                }
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn(){
            items(notes) {note->
                NoteRow(note = note,
                    onNoteClicked = {
                        onRemoveNote(note)
                })
            }
        }
    }
}

@Composable
fun NoteRow(modifier: Modifier = Modifier, note : Note, onNoteClicked : (Note)-> Unit) {
    Surface(
        modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp)),
    color = MaterialTheme.colors.secondary,
    elevation = 6.dp) {
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(text = formatDate(note.entryDate.time),
            style = MaterialTheme.typography.caption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}