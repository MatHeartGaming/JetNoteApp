package it.spaarkly.jetnoteapp.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import it.spaarkly.jetnoteapp.data.NotesDataSource
import it.spaarkly.jetnoteapp.model.Note
import it.spaarkly.jetnoteapp.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    //private var noteList = mutableStateListOf<Note>()
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged().collect{ listOfNotes ->
                _noteList.value = listOfNotes
            }
        }
    }

    fun addNote(note : Note) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun removeNote(note : Note) = viewModelScope.launch {
        repository.deleteNote(note)
        Log.d("On Remove", "removeNote: ${note.title}, ${note.description}, ${note.id}")
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }

}