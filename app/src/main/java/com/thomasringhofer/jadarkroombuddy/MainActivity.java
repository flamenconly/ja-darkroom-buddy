package com.thomasringhofer.jadarkroombuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.thomasringhofer.jadarkroombuddy.database.AppDatabase;
import com.thomasringhofer.jadarkroombuddy.database.DevelopmentProcessDao;
import com.thomasringhofer.jadarkroombuddy.databinding.DevelopmentProcessItemBinding;
import com.thomasringhofer.jadarkroombuddy.entities.DevelopmentProcess;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Database
        AppDatabase.CreateInstance(getApplicationContext());
        //TODO: Remove when developing is done to release mode
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.GetInstance().recreateData();
            }
        }).start();

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
    protected void onStart() {
        super.onStart();
        new Thread(new LoadAllDevelopmentProcessTask()).start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new LoadAllDevelopmentProcessTask()).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu_resources,menu);

        return true;
    }

    private class LoadAllDevelopmentProcessTask implements Runnable{
        @Override
        public void run() {

            DevelopmentProcessDao dao = AppDatabase.GetInstance().developmentProcessDao();
            final ListView viewGroup = findViewById(R.id.listview);

            for (final DevelopmentProcess process:dao.LoadAllProcesses()) {

                Runnable task = new Runnable() {
                    @Override
                    public void run() {
                        DevelopmentProcessItemBinding binding = DevelopmentProcessItemBinding.inflate(getLayoutInflater(),viewGroup,false);
                        binding.setProcess(process);
                    }
                };

                viewGroup.post(task);
            }
        }
    }

}
