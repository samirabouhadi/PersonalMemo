package com.example.blocnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class MainActivity5 extends AppCompatActivity {
    //db
    Button btn;
    EditText e1;
    EditText e2;
    loginDB PP;
    //db

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        getSupportActionBar().hide();


        //db
        btn=findViewById(R.id.con);
        e1=findViewById(R.id.edit32);
        e2=findViewById(R.id.edit42);
        PP=new loginDB(this);
        PP.open();

        //recupere email
        // Récupérer le texte de l'EditText
        String donnees = e1.getText().toString();

        // Créer un Intent pour passer à l'activité suivante
       // Intent intent = new Intent(this, MainActivity8.class);

        // Ajouter les données à l'Intent
        /*intent.putExtra("EMAIL_DATA", donnees);
        Toast.makeText(MainActivity5.this, "Please fill all fields" +donnees
                , Toast.LENGTH_SHORT).show();
        // Démarrer l'activité suivante
                startActivity(intent);*/
        //recup em
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=e1.getText().toString();
                String passeff=e2.getText().toString();
                if(email.equals("")||passeff.equals(""))
                    Toast.makeText(MainActivity5.this, "Please fill all fields" +
                            "3atory", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUserInfo = PP.checkEmailPasse(email,passeff);
                    if(checkUserInfo == false){
                        Toast.makeText(MainActivity5.this,"You need to register first",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity5.this,"Login successful! Welcome back",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
                        intent.putExtra("EMAIL_DATA", email);
                        startActivity(intent);
                    }
                }
            }
        });
        //db

        /*Button conn = findViewById(R.id.con);
        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers une autre activité
                Intent intent = new Intent(MainActivity5.this, MainActivity3.class);
                startActivity(intent);
                //Et Display Toast
                Toast.makeText(MainActivity5.this,
                        "Login successful! Welcome back",
                        Toast.LENGTH_LONG).show();
            }
        });*/

    }

}