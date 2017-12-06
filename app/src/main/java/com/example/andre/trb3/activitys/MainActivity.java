package com.example.andre.trb3.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.andre.trb3.R;
import com.example.andre.trb3.dbhelper.TarefaDAO;
import com.example.andre.trb3.objects.Tarefa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listaTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTarefas = (ListView) findViewById(R.id.lv_tarefas);
        registerForContextMenu(listaTarefas);

        Button btnCadastrarTarefa = (Button) findViewById(R.id.btn_cadastrar_tarefa);
        Button btnTags = (Button) findViewById(R.id.btn_tags);

        startActivityOnClick(btnCadastrarTarefa, CadastrarTarefa.class);
        startActivityOnClick(btnTags, Tags.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        recarregarListaTarefas();

        listaTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tarefa tarefa = (Tarefa) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, CadastrarTarefa.class);
                intent.putExtra("TAREFA", tarefa);
                startActivity(intent);
            }
        });
    }

    private void recarregarListaTarefas() {
        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        ArrayList<Tarefa> tarefas = tarefaDAO.buscarTarefas();
        tarefaDAO.close();

        ArrayAdapter<Tarefa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tarefas);
        listaTarefas.setAdapter(adapter);
    }

    private void startActivityOnClick(Button btn, final Class activity) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), activity);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem remover = menu.add("Remover");
        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Tarefa tarefa = (Tarefa) listaTarefas.getItemAtPosition(info.position);

                TarefaDAO tareDAO = new TarefaDAO(getApplicationContext());
                tareDAO.removerTarefa(tarefa);
                tareDAO.close();

                recarregarListaTarefas();

                return false;
            }
        });
    }
}
