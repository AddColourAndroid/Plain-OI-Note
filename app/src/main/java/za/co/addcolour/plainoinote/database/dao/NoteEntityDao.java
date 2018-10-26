package za.co.addcolour.plainoinote.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import za.co.addcolour.plainoinote.database.model.NoteEntity;

@Dao
public interface NoteEntityDao {

    // INSERT AND UPDATE NOTE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteEntity noteEntity);

    // INSERT AND UPDATE ALL NOTE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNotes(List<NoteEntity> noteEntity);

    // DELETE NOTE
    @Delete
    void deleteNote(NoteEntity noteEntity);

    // GET NOTE BY ID
    @Query("SELECT * FROM note_entity WHERE id = :id")
    NoteEntity getNoteById(int id);

    // GET ALL NOTES
    @Query("SELECT * FROM note_entity ORDER BY date DESC")
    LiveData<List<NoteEntity>> getAllNotes();

    // DELETE ALL NOTES
    @Query("DELETE FROM note_entity")
    int deleteAllNotes();

    // GET NOTE COUNT
    @Query("SELECT COUNT(*) FROM note_entity")
    int getNoteCount();
}