package com.example.andre.trb3.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 2;

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.Tarefa.SQL_CREATE_TAREFA);
        db.execSQL(DBContract.Tag.SQL_CREATE_TAG);
        db.execSQL(DBContract.Auxiliar.SQL_CREATE_AUXILIAR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContract.Tarefa.SQL_DROP_TAREFA);
        db.execSQL(DBContract.Tag.SQL_DROP_TAG);
        db.execSQL(DBContract.Auxiliar.SQL_DROP_AUXILIAR);
        onCreate(db);
    }
}
