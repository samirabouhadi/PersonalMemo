package com.example.blocnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity7 extends AppCompatActivity {
    private int requestCode;
    private EditText editText;
    private ImageButton buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main7);

        TextView textView = findViewById(R.id.textViewInBlocNotes);
        ImageButton back = findViewById(R.id.buttonBack);
        editText = findViewById(R.id.edittext);
        buttonSave = findViewById(R.id.buttonSave);

        String cardText = getIntent().getStringExtra("cardText");
        textView.setText(cardText);

        requestCode = getIntent().getIntExtra("requestCode", 0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String textToSave = editText.getText().toString();

                Intent returnIntent = new Intent();
                returnIntent.putExtra("textFromNotes", textToSave);
                returnIntent.putExtra("requestCode", requestCode); // Pass the request code back
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
