package com.example.blocnoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity8 extends AppCompatActivity {

    private ArrayList<TaskModel> taskList;
    private TaskAdapter taskAdapter;
    loginDB my_DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        getSupportActionBar().hide();

        //Showtodo

        //Showtodo

        //db
        my_DB = new loginDB(this);
        my_DB.open();
        ListView listView = findViewById(R.id.listView);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        //int id = getIntent().getIntExtra("id",0);
        //int in = my_DB.getCurrId("oo");
        //Toast.makeText(MainActivity8.this, "!"+id, Toast.LENGTH_LONG).show();
        //Toast.makeText(MainActivity8.this, "!"+in, Toast.LENGTH_LONG).show();
        //my_DB.getAllTasks(id);
        //db
        //home
        ImageView ho = findViewById(R.id.homeIcon);
        ho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity8.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        //home
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        listView.setAdapter(taskAdapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTaskDialog();
            }
        });


        //Gérer le clic sur un élément pour la suppression
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                final TaskModel task = taskList.get(position);
                CheckBox checkBox = view.findViewById(R.id.checkBox);

                // Attach a listener to the CheckBox
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        // Check if the checkbox is now checked
                        if (isChecked) {
                            // Show confirmation dialog
                            showConfirmationDialog(position);
                        } else {
                            // If the checkbox is unchecked, perform other action if needed
                            // In this example, do nothing
                        }

                        // Update the checked state in the TaskModel
                        task.setChecked(isChecked);
                    }
                });

                // Toggle the checked state programmatically
                //checkBox.setChecked(!checkBox.isChecked());
                checkBox.setChecked(checkBox.isChecked());
            }
        });
        /*String email = getIntent().getStringExtra("EMAIL_DATA");
        int inp = my_DB.getCurrId(email);*/
        /*my_DB.getTasksForUser(inp);*/
        /*my_DB.getTaskById(inp);*/
       // showContenuTodo();
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add task");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //int idp  = getIntent().getIntExtra("id",0);

                String taskDescription = input.getText().toString();
                if (!taskDescription.isEmpty()) {
                    taskList.add(new TaskModel(taskDescription));
                    taskAdapter.notifyDataSetChanged();

                    //recupe email
                    String email = getIntent().getStringExtra("EMAIL_DATA");
                    //recupe email
                    Toast.makeText(MainActivity8.this, " !"+email, Toast.LENGTH_LONG).show();

                    int inp = my_DB.getCurrId(email);

                    boolean isInserted = my_DB.insertTask(taskDescription,inp);
                    //Toast.makeText(MainActivity.this, "Tâche ajoutée!", Toast.LENGTH_LONG).show();
                    if (isInserted) {

                        Toast.makeText(MainActivity8.this, "Tâche ajoutée!", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(MainActivity8.this, "Erreur lors de l'ajout de la tâche.", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(MainActivity8.this, "Veuillez entrer une description de tâche valide.", Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.show();
    }

    private void showConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity8.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Do you want to delete this task?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Delete task
                TaskModel task = taskList.get(position);
                taskList.remove(position);
                taskAdapter.notifyDataSetChanged();

                // Delete from database
                my_DB.deleteTask(task.getId());

                // Show "Well done" toast
                Toast.makeText(MainActivity8.this, "Well done!", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Do nothing on cancel
            }
        });
        builder.show();
    }

    //ha
   /* public void showContenuTodo() {


        // Utilisez la méthode getAllTasks pour obtenir un curseur sur toutes les tâches
        Cursor cursor = my_DB.getAllTasks();

        // Liste pour stocker les objets TaskModel
        List<TaskModel> taskList = new ArrayList<>();

        // Vérifiez si le curseur est valide et s'il y a des données
        if (cursor != null && cursor.moveToFirst()) {
            int columnContenuTodo = cursor.getColumnIndexOrThrow(MaBaseOpenHelper.COLONNE_contenutodo);

            do {
                // Récupérez la valeur de la colonne pour chaque enregistrement
                String contenuTodo = cursor.getString(columnContenuTodo);

                // Ajoutez un nouvel objet TaskModel à la liste
                taskList.add(new TaskModel(contenuTodo));

            } while (cursor.moveToNext());

            // N'oubliez pas de fermer le curseur après utilisation
            cursor.close();
        }

        // Créez un adaptateur personnalisé pour lier la liste d'objets à la ListView
        TaskAdapter taskAdapter = new TaskAdapter(this, taskList);

        // Obtenez la référence de votre ListView
        ListView listView = findViewById(R.id.listView);

        // Définissez l'adaptateur personnalisé sur la ListView
        listView.setAdapter(taskAdapter);*/
    }




