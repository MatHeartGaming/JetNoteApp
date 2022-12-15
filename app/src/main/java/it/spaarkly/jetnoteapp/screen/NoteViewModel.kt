package it.spaarkly.jetnoteapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import it.spaarkly.jetnoteapp.data.NotesDataSource
import it.spaarkly.jetnoteapp.model.Note

class NoteViewModel : ViewModel() {

    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NotesDataSource().loadNotes())
        noteList.addAll(NotesDataSource().loadNotes())
    }

    fun addNote(note : Note) {
        noteList.add(note)
    }

    fun removeNote(note : Note) {
        noteList.remove(note)
    }

    fun getAllNotes() : List<Note> {
        return noteList
    }

}