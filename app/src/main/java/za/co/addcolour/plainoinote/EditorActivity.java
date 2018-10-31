package za.co.addcolour.plainoinote;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

import za.co.addcolour.plainoinote.databinding.ActivityEditorBinding;
import za.co.addcolour.plainoinote.model.NoteEntity;
import za.co.addcolour.plainoinote.viewmodel.EditorViewModel;

import static za.co.addcolour.plainoinote.utils.Constants.EDITING_KEY;
import static za.co.addcolour.plainoinote.utils.Constants.NOTE_ID_KEY;

public class EditorActivity extends AppCompatActivity {

    private ActivityEditorBinding mBinding;
    private EditorViewModel mViewModel;

    private boolean mNewNote, mEditing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_editor);

        setSupportActionBar(mBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_check);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState != null)
            mEditing = savedInstanceState.getBoolean(EDITING_KEY);

        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(EditorViewModel.class);

        mViewModel.mLiveNoteEntity.observe(this, new Observer<NoteEntity>() {
            @Override
            public void onChanged(@Nullable NoteEntity noteEntity) {
                if (noteEntity != null && !mEditing) {
                    mBinding.contentEditor.editTextNote.setText(noteEntity.getText());
                }
            }
        });

        mNewNote = true;
        setTitle(getString(R.string.new_note));
        if (getIntent().getExtras() != null) {
            setTitle(getString(R.string.edit_note));
            int noteId = getIntent().getExtras().getInt(NOTE_ID_KEY);
            mViewModel.loadData(noteId);
            mNewNote = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNewNote) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_editor, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndReturn();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            Toast.makeText(this, getString(R.string.note_deleted_successfully), Toast.LENGTH_SHORT).show();
            mViewModel.deleteNote();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        saveAndReturn();
    }

    private void saveAndReturn() {
        if (!TextUtils.isEmpty(mBinding.contentEditor.editTextNote.getText())) {
            Toast.makeText(this, getString(R.string.note_save_successfully), Toast.LENGTH_SHORT).show();
            mViewModel.saveNote(mBinding.contentEditor.editTextNote.getText().toString());
        }
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(EDITING_KEY, true);
        super.onSaveInstanceState(outState);
    }
}