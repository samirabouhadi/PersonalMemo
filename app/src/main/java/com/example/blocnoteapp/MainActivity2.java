package com.example.blocnoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity2 extends AppCompatActivity {
    DatePicker datePicker;
    Button btnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // getSupportActionBar().hide();


        ImageView MyImg = findViewById(R.id.Img2);
        MyImg.setImageResource(R.drawable.initpage);

        //datePicker
        datePicker = findViewById(R.id.datep);
        btnd = findViewById(R.id.pbtn);

        btnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day = "The day today is: "+ datePicker.getDayOfMonth();
                String month = "/"+ (datePicker.getMonth()+1);
                String year = "/"+ datePicker.getYear();

                Toast.makeText(
                        MainActivity2.this,
                        day +" "+ month +" " + year,
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    //move to another activity by clicking in option one option in the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate
        getMenuInflater().inflate(R.menu.my_menu, menu);

        //move to another activity(workspace) by clicking in option workeSpace in the menu
        MenuItem workspaceItem = menu.findItem(R.id.wrk);
        workspaceItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                String email = getIntent().getStringExtra("EMAIL_DATA");
                Intent intent=new Intent(MainActivity2.this, MainActivity3.class);
                intent.putExtra("EMAIL_DATA",email);
                startActivity(intent);
                return true;
            }
        });

        //move to another activity(welcomePage) by clicking in option Log out in the menu
        MenuItem Logout = menu.findItem(R.id.logOut);
        Logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                return true;
            }
        });

        //move to another activity(welcomePage) by clicking in option Log out in the menu
        MenuItem HomePage = menu.findItem(R.id.hom);
        HomePage.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                return true;
            }
        });
        return true;
    }
}