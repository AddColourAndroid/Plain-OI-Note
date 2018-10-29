package za.co.addcolour.plainoinote.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.VisibleForTesting;

import za.co.addcolour.plainoinote.db.converter.DateConverter;
import za.co.addcolour.plainoinote.db.dao.NoteEntityDao;
import za.co.addcolour.plainoinote.model.NoteEntity;

@Database(entities = {NoteEntity.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    @VisibleForTesting
    private static final String DATABASE_NAME = "basic-sample-db";

    private static final Object LOCK = new Object();

    public abstract NoteEntityDao noteEntityDao();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                if (sInstance == null) {
                    sInstance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME).build();
                }
            }
        }
        return sInstance;
    }
}