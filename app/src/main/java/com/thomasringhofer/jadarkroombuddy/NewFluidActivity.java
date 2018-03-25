package com.thomasringhofer.jadarkroombuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.thomasringhofer.jadarkroombuddy.common.AsynchronousHandler;
import com.thomasringhofer.jadarkroombuddy.common.CaseInsensitiveSet;
import com.thomasringhofer.jadarkroombuddy.common.TextValidator;
import com.thomasringhofer.jadarkroombuddy.database.AbstractSaveItemTask;
import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.entities.Fluid;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewFluidActivity extends AppCompatActivity implements AsynchronousHandler {

    @BindView(R.id.editName)
    EditText editName;

    @BindView(R.id.editType)
    Spinner editType;

    @BindView(R.id.editColorType)
    Spinner editColorType;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_fluid);

        ButterKnife.bind(this);

        editName.addTextChangedListener(new TitleTextValidator(editName));
        setSupportActionBar(toolbar);

        // Init Type Spinner
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.fluid_type_values, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editType.setAdapter(spinnerAdapter);

        // Init ColorType Spinner
        spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.color_type_values,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editColorType.setAdapter(spinnerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_film_menu_resources, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_action:
                if(editName.getError()!=null){
                    return false;
                }
                return saveItem();
            case R.id.cancel_action:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onSuccessCallback() {

        getCurrentFocus().post(new Runnable() {
            @Override
            public void run() {
                onBackPressed();
            }
        });

    }

    private boolean saveItem(){

        Fluid item = new Fluid();
        item.setTitle(editName.getText().toString());
        item.setType(editType.getSelectedItem().toString());
        item.setColorType(editColorType.getSelectedItem().toString());

        new Thread(new SaveItemTask(item,this)).start();

        return true;

    }

    @Override
    public void onFailureCallback() {
        // Do nothing.
    }

    private final class TitleTextValidator extends TextValidator{

        private Set<String> existingNames;

        public  TitleTextValidator(EditText editText){
            super(editText);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase database = AppDatabase.GetInstance();
                    existingNames = new CaseInsensitiveSet(database.fluidDao().getTitles());
                }
            }).start();
        }

        @Override
        public void validate(EditText editText, String text) {

            editText.setError(null);

            if(existingNames.contains(text)){
                editText.setError(getString(R.string.name_already_exists));
            }
        }
    }

    private final class SaveItemTask extends AbstractSaveItemTask<Fluid>{

        public SaveItemTask(Fluid item,AsynchronousHandler asynchronousHandler){
            super(item,asynchronousHandler);
        }

        @Override
        protected boolean saveItem(Fluid item) {
            AppDatabase db = AppDatabase.GetInstance();
            long id = db.fluidDao().insertFluid(item);
            item.setId(id);

            return id>0;
        }

        @Override
        protected boolean itemCanBeSaved(Fluid item) {

            AppDatabase db = AppDatabase.GetInstance();
            if(item.getTitle()==null || item.getType()==null)return false;

            return db.fluidDao().getItemByTitle(item.getTitle()) == null;
        }

    }
}
