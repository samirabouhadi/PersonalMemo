package com.example.blocnoteapp;

import static com.example.blocnoteapp.MaBaseOpenHelper.COLONNE_contenuBloc;
import static com.example.blocnoteapp.MaBaseOpenHelper.COLONNE_titrebloc;

import android.app.Person;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;

import com.example.blocnoteapp.MaBaseOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class loginDB {
    public static final String TABLE_PERSON = "person";
    public static final String COLONNE_ID = "id";
    public static final String COLONNE_NOM = "nom";
    public static final String COLONNE_PRENOM = "prenom";
    public static final String COLONNE_EMAIL = "email";
    public static final String COLONNE_PASSE = "passe";
    public static final String COLONNE_contenutodo = "contenu_todo";

    private SQLiteDatabase BD;//db
    public static final int VERSION = 1;

    private static final String db_name = "db";
    private MaBaseOpenHelper baseOpenHelper;

    public loginDB(Context ctx) {
        baseOpenHelper = new MaBaseOpenHelper(ctx, db_name, null, VERSION);
    }

    public SQLiteDatabase open() {
        BD = baseOpenHelper.getWritableDatabase();//pour prendre une copy de db li creanaha
        return BD;
    }

    public void close() {
        BD.close();
    }

    public Boolean insert(String nom, String prenom, String email, String passe) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLONNE_NOM, nom);
        contentValues.put(COLONNE_PRENOM, prenom);
        contentValues.put(COLONNE_EMAIL, email);
        contentValues.put(COLONNE_PASSE, passe);
        Long result = BD.insert(TABLE_PERSON, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkInfo(String email) {
        Cursor cursor = BD.rawQuery("Select * from " + TABLE_PERSON + " where  email = ?  ", new String[]{email});
        // Cursor cursor = BD.rawQuery("Select * from " + TABLE_PERSON + " where nom = ? or prenom = ? and email = ? or passe = ? ", new String[]{nom, prenom, email, passe});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPasse(String email, String passe) {
        Cursor cursor = BD.rawQuery("Select * from " + TABLE_PERSON + " where  email = ? and passe = ? ", new String[]{email, passe});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    //to do list
    //lakh
    public boolean insertTask(String contenuTodo, int id) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLONNE_contenutodo, contenuTodo);

        String whereClause = "id = ?";
        String[] whereArgs = {String.valueOf(id)};

        int numberOfRowsUpdated = BD.update(TABLE_PERSON, contentValues, whereClause, whereArgs);

        return numberOfRowsUpdated > 0; // Si numberOfRowsUpdated > 0, la mise à jour a réussi, sinon elle a échoué.
    }

    //laak
    /*public boolean insertTask(String contenuTodo, String email) {
        int idUtilisateur = getCurrId(email);

        if (idUtilisateur != 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLONNE_contenutodo, contenuTodo);
            contentValues.put(COLONNE_ID, idUtilisateur);

            long result = BD.insert(TABLE_PERSON, null, contentValues);

            // Fermez le curseur si vous en avez un
            // Ceci est particulièrement important pour les ressources
            // Ne fermez pas le curseur de getCurrId, car il est déjà fermé dans cette méthode
            // cursor.close();

            return result != -1; // Si result est -1, l'insertion a échoué, sinon elle a réussi.
        }

        return false; // Si l'ID est 0, l'insertion a échoué.
    }*/


    public int getCurrId(String email) {
        int getid = 0;
        Cursor cursor = BD.rawQuery("SELECT * FROM " + TABLE_PERSON + " WHERE email = ?", new String[]{email});

        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex("id");
            if (columnIndex != -1) {
                getid = cursor.getInt(columnIndex);
            }
        }
        cursor.close(); // Assurez-vous de fermer le curseur
        return getid;
    }


    public Cursor getAllTasks() {

    return BD.query(TABLE_PERSON, null,null, null, null, null, null);
    }

    public void deleteTask(long taskId) {
        BD.delete(TABLE_PERSON, COLONNE_ID + " = ?", new String[]{String.valueOf(taskId)});
    }

    // db bloc note
    // Méthode pour insérer une nouvelle note
    public long insertNote(String title, String content) {
        ContentValues values = new ContentValues();
        values.put(COLONNE_titrebloc, title);
        values.put(COLONNE_contenuBloc, content);
        long newRowId = BD.insert(TABLE_PERSON, null, values);
        BD.close();
        return newRowId;
    }

    public void deleteNote(long id) {
        BD.delete("notes", "id=?", new String[]{String.valueOf(id)});
        BD.close();
    }

    public String re(String a, String b) {
        // Récupérer toutes les notes
        Cursor cursor = BD.query("notes", new String[]{"_id", "title", "content"}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
        }
        cursor.close();
        BD.close();
        return a;
    }

}