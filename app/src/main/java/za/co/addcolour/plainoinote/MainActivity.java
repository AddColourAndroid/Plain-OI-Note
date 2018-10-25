package za.co.addcolour.plainoinote;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import za.co.addcolour.plainoinote.databinding.ActivityMainBinding;
import za.co.addcolour.plainoinote.model.NoteEntity;
import za.co.addcolour.plainoinote.ui.adapter.NoteEntityAdapter;
import za.co.addcolour.plainoinote.ui.clickCallback.NoteEntityClickCallback;
import za.co.addcolour.plainoinote.utils.SampleData;

public class MainActivity extends AppCompatActivity implements NoteEntityClickCallback {

    private ActivityMainBinding mBinding;

    private LinearLayoutManager mManager;

    private NoteEntityAdapter mAdapter;
    private ArrayList<NoteEntity> noteEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(mBinding.toolbar);
        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        noteEntities = new ArrayList<>();
        noteEntities.addAll(SampleData.getNotes());

        initialize();
    }

    private void initialize() {
        mAdapter = new NoteEntityAdapter(this);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true);

        mBinding.contentMain.recyclerView.setHasFixedSize(true);
        mBinding.contentMain.recyclerView.setLayoutManager(mManager);

        mAdapter.setNoteEntityList(noteEntities);
        mBinding.contentMain.recyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(NoteEntity noteEntity) {

    }
}