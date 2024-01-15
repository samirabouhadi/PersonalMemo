package com.example.blocnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();


        //Move to Bloc Note
        Button BlocN = findViewById(R.id.buttonBloc);
        BlocN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity6.class);
                startActivity(intent);
            }
        });

        //Move to To Do List
        Button ToDo = findViewById(R.id.todol);
        ToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getIntent().getStringExtra("EMAIL_DATA");
                Intent intent=new Intent(MainActivity3.this, MainActivity8.class);
                intent.putExtra("EMAIL_DATA",email);
                startActivity(intent);
            }
        });




    }
}