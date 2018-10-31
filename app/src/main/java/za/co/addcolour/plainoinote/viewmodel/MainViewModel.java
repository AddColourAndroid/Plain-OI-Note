package za.co.addcolour.plainoinote.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import za.co.addcolour.plainoinote.db.repo.AppRepository;
import za.co.addcolour.plainoinote.model.NoteEntity;

public class MainViewModel extends AndroidViewModel {

    public LiveData<List<NoteEntity>> mNotesEntity;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mNotesEntity = mRepository.mNotes;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }

    public void deleteAllNotes() {
        mRepository.deleteAllNotes();
    }
}