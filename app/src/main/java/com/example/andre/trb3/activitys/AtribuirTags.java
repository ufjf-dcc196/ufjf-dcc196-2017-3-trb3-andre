package com.example.andre.trb3.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.andre.trb3.R;
import com.example.andre.trb3.dbhelper.AuxiliarDAO;
import com.example.andre.trb3.dbhelper.TagDAO;
import com.example.andre.trb3.dbhelper.TarefaDAO;
import com.example.andre.trb3.objects.Tag;
import com.example.andre.trb3.objects.Tarefa;

import java.util.ArrayList;

public class AtribuirTags extends AppCompatActivity {
    private Spinner spnTarefas;
    private Spinner spnTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atribuir_tags);

        spnTarefas = (Spinner) findViewById(R.id.spn_tarefas);
        spnTags = (Spinner) findViewById(R.id.spn_tags);

        Button btnAtribuir = (Button) findViewById(R.id.btn_atribuir);
        btnAtribuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atribuirTag();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        ArrayList<Tarefa> tarefas = tarefaDAO.buscarTarefas();
        tarefaDAO.close();

        ArrayAdapter<Tarefa> adapterTarefas = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        spnTarefas.setAdapter(adapterTarefas);

        TagDAO tagDAO = new TagDAO(getApplicationContext());
        ArrayList<Tag> tags = tagDAO.buscarTags();
        tagDAO.close();

        ArrayAdapter<Tag> adapterTags = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tags);
        spnTags.setAdapter(adapterTags);
    }

    private void atribuirTag() {
        Tarefa tarefa = (Tarefa) spnTarefas.getSelectedItem();
        Tag tag = (Tag) spnTags.getSelectedItem();

        AuxiliarDAO auxiliarDAO = new AuxiliarDAO(getApplicationContext());
        auxiliarDAO.inserirAtribuicao(tarefa, tag);
        auxiliarDAO.close();

        Toast.makeText(getApplicationContext(), "Atribuído com sucesso!", Toast.LENGTH_SHORT).show();
    }
}
