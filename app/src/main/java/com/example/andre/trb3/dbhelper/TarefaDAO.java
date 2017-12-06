package com.example.andre.trb3.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.andre.trb3.objects.Tarefa;
import java.util.ArrayList;

public class TarefaDAO extends DBHelper {

    public TarefaDAO(Context context) {
        super(context);
    }

    public void inserirTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = pegarDadosTarefa(tarefa);
        db.insert(DBContract.Tarefa.TABLE_NAME, null, values);
    }

    @NonNull
    private ContentValues pegarDadosTarefa(Tarefa tarefa) {
        ContentValues values = new ContentValues();
        values.put(DBContract.Tarefa.COLUMN_TITULO, tarefa.getTitulo());
        values.put(DBContract.Tarefa.COLUMN_DESCRICAO, tarefa.getDescricao());
        values.put(DBContract.Tarefa.COLUMN_DIFICULDADE, tarefa.getDificuldade());
        values.put(DBContract.Tarefa.COLUMN_ESTADO, tarefa.getEstado());
        return values;
    }

    public ArrayList<Tarefa> buscarTarefas() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DBContract.Tarefa.SQL_SELECT_TAREFAS, null);

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

    public void alterarTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = pegarDadosTarefa(tarefa);
        String[] id = {tarefa.getId().toString()};
        db.update(DBContract.Tarefa.TABLE_NAME, values, DBContract.Tarefa._ID+" = ?", id);
    }

    public void removerTarefa(Tarefa tarefa) {
        SQLiteDatabase db = getWritableDatabase();
        String[] id = {tarefa.getId().toString()};
        db.delete(DBContract.Tarefa.TABLE_NAME, DBContract.Tarefa._ID+" = ?", id);
    }
}
