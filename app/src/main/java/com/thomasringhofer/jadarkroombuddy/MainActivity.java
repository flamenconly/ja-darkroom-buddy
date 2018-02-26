package com.thomasringhofer.jadarkroombuddy;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements DevelopmentProcessFragment.OnListFragmentInteractionListener {

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Setup Database first of all
        AppDatabase.CreateInstance(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Setup Widgets
        //Toolbar toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //TODO: Remove when developing is done to release mode
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.GetInstance().recreateData();
            }
        }).start();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent;

                switch (item.getItemId()){
                    case R.id.add_process:
                        intent = new Intent(MainActivity.this,AddDevelopmentProcessActivity.class);
                        break;
                    /*
                    case R.id.recent_processes:
                        intent = new Intent(MainActivity.this,RecentProcessesActivity.class);
                        break;
                    case R.id.manage_processes:
                        intent = new Intent(MainActivity.this,RecentProcessesActivity.class);
                        break;
                        */
                    default:
                        return false;
                }

                if(intent!=null)startActivity(intent);
                return true;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //new Thread(new LoadAllDevelopmentProcessTask()).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //new Thread(new LoadAllDevelopmentProcessTask()).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu_resources,menu);

        return true;
    }


    @Override
    public void onListFragmentInteraction(DevelopmentProcess item) {
        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(this);
        dlgBuilder.setMessage(item.getTitle());
        dlgBuilder.setCancelable(true);
        dlgBuilder.create().show();

    }
}
