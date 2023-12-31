package com.shadspace.spacenotes.database

import androidx.lifecycle.LiveData
import com.shadspace.spacenotes.model.Note

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun update(note: Note) {
        if (note.id != null) {
            noteDao.update(note.id, note.title, note.note)
        } else {
            // Handle the case where the note ID is null, maybe throw an exception or log an error
        }
    }
}
