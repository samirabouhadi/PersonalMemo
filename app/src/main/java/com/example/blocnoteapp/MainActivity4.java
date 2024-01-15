package com.example.blocnoteapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    Button button;
    loginDB PP;
    EditText nom;
    EditText prenom;
    EditText email;
    EditText passe;
    EditText passeConf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();

        //bd
        button=findViewById(R.id.btn);
        PP=new loginDB(this);
        PP.open();
        nom=findViewById(R.id.edit2);
        prenom=findViewById(R.id.edit3);
        email=findViewById(R.id.edit4);
        passe=findViewById(R.id.edit5);
        passeConf=findViewById(R.id.edit6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomm = nom.getText().toString();
                String prenomm = prenom.getText().toString();
                String emaill = email.getText().toString();
                String passef = passe.getText().toString();

                String passefconf = passeConf.getText().toString();




                if(emaill.equals("")||passef.equals("")||passefconf.equals("")||nomm.equals("")||prenomm.equals("")) {
                    Toast.makeText(MainActivity4.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                } else{
                    if(passef.equals(passefconf)){
                        Boolean checkUserEmail = PP.checkInfo(emaill);
                        if(checkUserEmail == false){
                            Boolean insert = PP.insert(nomm,prenomm,emaill,passef);
                            if(insert == true) {

                                Intent intent = new Intent(getApplicationContext(), MainActivity5.class);
                                int putid = PP.getCurrId(emaill);
                                intent.putExtra("id", putid);
                                startActivity(intent);

                                Toast.makeText(MainActivity4.this, "h"+putid, Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(MainActivity4.this, "Already used!", Toast.LENGTH_SHORT).show();

                        }
                    }}}   });
        //bd

        /*Button insc = findViewById(R.id.btn);
        //Display toast when we click on connection
        insc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
                startActivity(intent);
                Toast.makeText(MainActivity4.this,
                        "Welcome! Your registration has been confirmed! ",
                        Toast.LENGTH_LONG).show();
            }
        });*/
    }
}
