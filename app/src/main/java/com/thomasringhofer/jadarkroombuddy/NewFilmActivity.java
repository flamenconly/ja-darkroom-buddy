package com.thomasringhofer.jadarkroombuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.thomasringhofer.jadarkroombuddy.common.CaseInsensitiveSet;
import com.thomasringhofer.jadarkroombuddy.common.TextValidator;
import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.entities.Film;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewFilmActivity extends AppCompatActivity {

    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editIso)
    EditText editIso;
    @BindView(R.id.editType)
    Spinner editType;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    private Film film;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_film);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.color_type_values, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editType.setAdapter(spinnerAdapter);

        editName.addTextChangedListener(new NameTextWatcher(editName));
        editIso.addTextChangedListener(new IsoNameWatcher(editIso));
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
                back();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void back() {
        onBackPressed();
    }

    private boolean saveItem() {

        new Thread(new SaveItemTask(getCurrentFocus())).start();

        return true;
    }

    private class SaveItemTask implements Runnable {

        private View view;

        public SaveItemTask(View view) {
            if(view == null)throw new IllegalArgumentException("view must not be null!");
            this.view = view;
        }

        @Override
        public void run() {
            AppDatabase appDatabase = AppDatabase.GetInstance();

            Film testFilm = appDatabase.filmDao().getFilmByName(editName.getText().toString());

            if (testFilm != null ||
                    editName.getText().toString().isEmpty() ||
                    editIso.getText().toString().isEmpty()) {
            } else {

                Film film = new Film();
                film.setName(editName.getText().toString());
                film.setIso(Integer.decode(editIso.getText().toString()));
                film.setType(editType.getSelectedItem().toString());

                appDatabase.filmDao().insertFilm(film);

                view.post(new Runnable() {
                    @Override
                    public void run() {
                        back();
                    }
                });
            }
        }
    }

    private class NameTextWatcher extends TextValidator{

        private Set<String> existingNames;

        public NameTextWatcher(EditText editText){
            super(editText);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    AppDatabase database = AppDatabase.GetInstance();
                    existingNames = new CaseInsensitiveSet(database.filmDao().getFilmNames());
                }
            }).start();
        }

        @Override
        public void validate(EditText editText, String text) {

            editText.setError(null);

            if(text.isEmpty()){
                editText.setError("You must provide a value!");
            }

            if(existingNames!=null) {
                if (existingNames.contains(text)) {
                    editText.setError(getString(R.string.name_already_exists));
                }
            }
        }
    }

    private class IsoNameWatcher extends TextValidator{

        public IsoNameWatcher(EditText editText){
            super(editText);
        }

        @Override
        public void validate(EditText editText, String text) {

            editText.setError(null);

            if(text.isEmpty()){
                editText.setError("You must provide a value!");
            }

        }
    }
}




