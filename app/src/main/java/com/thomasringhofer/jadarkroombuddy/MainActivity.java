package com.thomasringhofer.jadarkroombuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Database
        AppDatabase.CreateInstance(getApplicationContext());

        //Setup Widgets
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent;

                switch (item.getItemId()){
                    case R.id.recent_processes:
                        intent = new Intent(MainActivity.this,RecentProcessesActivity.class);
                        break;
                    case R.id.manage_processes:
                        intent = new Intent(MainActivity.this,RecentProcessesActivity.class);
                        break;
                    default:
                        return false;
                }

                if(intent!=null)startActivity(intent);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu_resources,menu);

        return true;
    }
}
