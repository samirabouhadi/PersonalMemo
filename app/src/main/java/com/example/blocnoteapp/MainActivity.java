package com.example.blocnoteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


       // ImageView MyImgView = findViewById(R.id.ImgHomePage);
        //using to change the image content programatically
       // MyImgView.setImageResource(R.drawable.welcome);

        //ImageView MyImgView1 = findViewById(R.id.homeIcon);
        //MyImgView1.setImageResource(R.drawable.home);

        //Move to second activity
        // this ImageView is the type of the view we create
        Button MyBtn2 = findViewById(R.id.login);  //initialize the view (by its ID)delared in xml by using findViewById() method
        MyBtn2.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                // Naviguer vers une autre activité
                Intent intent = new Intent(MainActivity.this, MainActivity5.class);
                startActivity(intent);
            }
        });

        //Move to second activity
        // this ImageView is the type of the view we create
        Button MyBtn1 = findViewById(R.id.MyBtn);  //initialize the view (by its ID)delared in xml by using findViewById() method
        MyBtn1.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                // Naviguer vers une autre activité
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });

    }
}
