package com.example.blocnoteapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MaBaseOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_PERSON = "person";
    public static final String COLONNE_ID = "id";
    public static final String COLONNE_NOM = "nom";
    public static final String COLONNE_PRENOM = "prenom";
    public static final String COLONNE_EMAIL = "email";
    public static final String COLONNE_PASSE = "passe";
    public static final String COLONNE_titrebloc = "titreB";
    public static final String COLONNE_contenuBloc = "contenu_bloc";
    public static final String COLONNE_icon = "iconbloc";
    public static final String COLONNE_contenutodo = "contenu_todo";
    //private static final String REQUETE_CREATION_TABLE = "create table "+TABLE_PERSONNE+ " ( " + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLONNE_NOM + " TEXT, " + COLONNE_PRENOM + " TEXT, " +COLONNE_EMAIL + " TEXT, " + COLONNE_PASSE + " TEXT, " + COLONNE_titrebloc + " TEXT, " + COLONNE_contenuBloc + " TEXT, " + COLONNE_contenutodo + " TEXT);";

    private static final String REQUETE_CREATION_TABLE = "create table " + TABLE_PERSON + " ( " + COLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLONNE_NOM + " TEXT, " + COLONNE_PRENOM + " TEXT, " + COLONNE_EMAIL + " TEXT, " + COLONNE_PASSE + " TEXT, " + COLONNE_titrebloc + " TEXT, " + COLONNE_contenuBloc + " TEXT, " + COLONNE_icon + " TEXT,  " + COLONNE_contenutodo + " TEXT);";


    public MaBaseOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);//file database ->constructeur pour creer db
    }
    //ajou user
    // Méthode pour enregistrer un nouvel utilisateur
    /*public void registerUser(String nom, String prenom, String email, String passe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLONNE_NOM, nom);
        values.put(COLONNE_PRENOM, prenom);
        values.put(COLONNE_EMAIL, email);
        values.put(COLONNE_PASSE, passe);

        long newRowId = db.insert(TABLE_PERSON, null, values);
        db.close();
    }

    // Méthode pour vérifier si un e-mail existe dans la base de données (pour la connexion)
    public boolean checkEmailExistence(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLONNE_EMAIL};
        String selection = COLONNE_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                TABLE_PERSON,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean emailExists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return emailExists;
    }
    //ajou user*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_TABLE);//creer la table et l'ajoute dans bd
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}