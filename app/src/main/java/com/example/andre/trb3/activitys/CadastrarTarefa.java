package com.example.andre.trb3.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.andre.trb3.R;
import com.example.andre.trb3.dbhelper.TarefaDAO;
import com.example.andre.trb3.objects.Tarefa;


public class CadastrarTarefa extends AppCompatActivity {
    private EditText etTitulo;
    private EditText etDescricao;
    private RatingBar rbDificuldade;
    private Spinner spnEstados;
    private ArrayAdapter<String> adapter;
    private Tarefa tarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tarefa);

        etTitulo = (EditText) findViewById(R.id.et_titulo);
        etDescricao = (EditText) findViewById(R.id.et_descricao);
        rbDificuldade = (RatingBar) findViewById(R.id.rb_dificuldade);
        spnEstados = (Spinner) findViewById(R.id.spn_estados);

        String[] estados = {"A fazer", "Em execução", "Bloqueada", "Concluída"};
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estados);
        spnEstados.setAdapter(adapter);

        tarefa = new Tarefa();
        if(getIntent().getSerializableExtra("TAREFA") != null){
            tarefa = (Tarefa) getIntent().getSerializableExtra("TAREFA");
            preencherDados();
        }

        Button btnSalvar = (Button) findViewById(R.id.btn_salvar_tarefa);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarTarefa();
            }
        });
    }

    private void preencherDados() {
        etTitulo.setText(tarefa.getTitulo());
        etDescricao.setText(tarefa.getDescricao());
        rbDificuldade.setProgress(tarefa.getDificuldade());
        spnEstados.setSelection(adapter.getPosition(tarefa.getEstado()));
    }

    private void salvarTarefa() {
        tarefa.setTitulo(etTitulo.getText().toString());
        tarefa.setDescricao(etDescricao.getText().toString());
        tarefa.setDificuldade(rbDificuldade.getProgress());
        tarefa.setEstado(spnEstados.getSelectedItem().toString());

        TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
        if(tarefa.getId() == null) {
            tarefaDAO.inserirTarefa(tarefa);
        }
        else {
            tarefaDAO.alterarTarefa(tarefa);
        }
        tarefaDAO.close();

        Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
