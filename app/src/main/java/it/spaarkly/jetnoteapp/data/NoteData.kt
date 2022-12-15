package it.spaarkly.jetnoteapp.data

import it.spaarkly.jetnoteapp.model.Note

class NotesDataSource {
    fun loadNotes() : List<Note> {
        return listOf(
            Note(title = "Note 1", description = "Note 1 descr"),
            Note(title = "Note 2", description = "Note 2 descr"),
            Note(title = "Note 3", description = "Note 3 descr"),
            Note(title = "Note 4", description = "Note 4 descr"),
            Note(title = "Note 5", description = "Note 5 descr"),
            Note(title = "Note 7", description = "Note 7 descr"),
            Note(title = "Note 8", description = "Note 8 descr"),
        )
    }
}