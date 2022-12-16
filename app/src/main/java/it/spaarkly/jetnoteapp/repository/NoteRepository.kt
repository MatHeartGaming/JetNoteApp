package it.spaarkly.jetnoteapp.repository

import it.spaarkly.jetnoteapp.data.NoteDatabaseDao
import it.spaarkly.jetnoteapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    suspend fun addNote(note : Note) = noteDatabaseDao.insert(note)

    fun getAllNotes() : Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun getNote(id : String) : Note = noteDatabaseDao.getNoteById(id)

    suspend fun updateNote(note : Note) = noteDatabaseDao.update(note)

    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)

    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()

}