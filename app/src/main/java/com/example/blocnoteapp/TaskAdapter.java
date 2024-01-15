package com.example.blocnoteapp;

// TaskAdapter.java
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskModel> {

    public TaskAdapter(Context context, List<TaskModel> tasks) {
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final TaskModel task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_task, parent, false);
        }

        final CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        TextView textView = convertView.findViewById(R.id.textView);

        checkBox.setChecked(task.isChecked());
        textView.setText(task.getDescription());

        // Set the OnCheckedChangeListener for the CheckBox
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show confirmation dialog
                    showConfirmationDialog(task);
                } else {
                    // If the checkbox is unchecked, perform other action if needed
                    // In this example, do nothing
                }

                // Update the checked state in the TaskModel
                task.setChecked(isChecked);
            }
        });

        return convertView;
    }

    private void showConfirmationDialog(final TaskModel task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirmation");
        builder.setMessage("Voulez-vous supprimer cette tâche ?");
        builder.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                remove(task);  // Remove the task from the adapter
                notifyDataSetChanged();

                // Show "Well done" toast
                Toast.makeText(getContext(), "Well done!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Uncheck the checkbox if the deletion is canceled
                task.setChecked(false);
                notifyDataSetChanged();
            }
        });
        builder.show();
    }
}



