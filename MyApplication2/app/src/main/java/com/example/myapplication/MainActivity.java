package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnEmployees, btnProjects, btnClients, btnOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEmployees = findViewById(R.id.btnEmployees);
        btnProjects = findViewById(R.id.btnProjects);
        btnClients = findViewById(R.id.btnClients);
        btnOrders = findViewById(R.id.btnOrders);

        btnEmployees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EmployeeActivity.class));
            }
        });

        btnProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProjectActivity.class));
            }
        });

        btnClients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClientActivity.class));
            }
        });

        btnOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
            }
        });
    }
}
