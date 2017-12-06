package com.example.andre.trb3.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andre.trb3.R;
import com.example.andre.trb3.dbhelper.AuxiliarDAO;
import com.example.andre.trb3.objects.Tag;
import com.example.andre.trb3.objects.Tarefa;

import java.util.ArrayList;

public class TarefasAssociadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefas_associadas);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Tag tag = (Tag) getIntent().getSerializableExtra("TAG");

        AuxiliarDAO auxiliarDAO = new AuxiliarDAO(getApplicationContext());
        ArrayList<Tarefa> tarefas = auxiliarDAO.buscarTarefasAssociadas(tag);
        auxiliarDAO.close();

        ListView listaTarefas = (ListView) findViewById(R.id.lv_tarefas_associadas);
        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        listaTarefas.setAdapter(adapter);
    }
}
