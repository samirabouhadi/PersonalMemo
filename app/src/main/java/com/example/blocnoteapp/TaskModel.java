package com.example.blocnoteapp;


public class TaskModel {
    private long id;
    private String description;
    private boolean checked;

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskModel(){

    }
    public TaskModel(String description) {
        this.description = description;
        this.checked = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long taskId) {
        this.id = taskId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}


