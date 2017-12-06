package com.example.andre.trb3.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.andre.trb3.objects.Tag;
import com.example.andre.trb3.objects.Tarefa;

import java.util.ArrayList;

public class AuxiliarDAO extends DBHelper {
    public AuxiliarDAO(Context context) {
        super(context);
    }

    public void inserirAtribuicao(Tarefa tarefa, Tag tag) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBContract.Auxiliar.COLUMN_TAREFA, tarefa.getId());
        values.put(DBContract.Auxiliar.COLUMN_TAG, tag.getId());

        db.insert(DBContract.Auxiliar.TABLE_NAME, null, values);
    }

    public ArrayList<Tarefa> buscarTarefasAssociadas(Tag tag) {
        SQLiteDatabase db = getReadableDatabase();

        final String sql = "SELECT * FROM "+DBContract.Tarefa.TABLE_NAME+
                " INNER JOIN "+DBContract.Auxiliar.TABLE_NAME+" ON "+
                DBContract.Tarefa.TABLE_NAME+"."+DBContract.Tarefa._ID+" = "+
                DBContract.Auxiliar.TABLE_NAME+"."+DBContract.Auxiliar.COLUMN_TAREFA+
                " WHERE "+DBContract.Auxiliar.TABLE_NAME+"."+DBContract.Auxiliar.COLUMN_TAG+" = ?";
        String[] id = {tag.getId().toString()};
        Cursor cursor = db.rawQuery(sql, id);

        ArrayList<Tarefa> tarefas = new ArrayList<>();
        while(cursor.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            tarefa.setId(cursor.getLong(cursor.getColumnIndex(DBContract.Tarefa._ID)));
            tarefa.setTitulo(cursor.getString(cursor.getColumnIndex(DBContract.Tarefa.COLUMN_TITULO)));
            tarefa.setDescricao(cursor.getString(cursor.getColumnIndex(DBContract.Tarefa.COLUMN_DESCRICAO)));
            tarefa.setDificuldade(cursor.getInt(cursor.getColumnIndex(DBContract.Tarefa.COLUMN_DIFICULDADE)));
            tarefa.setEstado(cursor.getString(cursor.getColumnIndex(DBContract.Tarefa.COLUMN_ESTADO)));

            tarefas.add(tarefa);
        }

        cursor.close();

        return tarefas;
    }
}
