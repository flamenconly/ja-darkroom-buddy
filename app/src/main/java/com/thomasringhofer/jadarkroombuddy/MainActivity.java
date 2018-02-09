package com.thomasringhofer.jadarkroombuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button recent_processes_btn;
    Button manage_processes_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recent_processes_btn = findViewById(R.id.recent_prcesses_btn);
        manage_processes_btn = findViewById(R.id.manage_processes_btn);

        recent_processes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecentProcessesActivity.class);
                startActivity(intent);
            }
        });

        manage_processes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RecentProcessesActivity.class);
                startActivity(intent);
            }
        });
    }

}
