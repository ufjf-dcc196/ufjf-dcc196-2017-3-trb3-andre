package com.example.andre.trb3.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andre.trb3.R;
import com.example.andre.trb3.dbhelper.TagDAO;
import com.example.andre.trb3.objects.Tag;

public class CadastrarTag extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tag);

        Button btnSalvar = (Button) findViewById(R.id.btn_salvar_tag);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarTag();
            }
        });
    }

    private void cadastrarTag() {
        Tag tag = new Tag();

        EditText etTag = (EditText) findViewById(R.id.et_tag);
        tag.setTag(etTag.getText().toString());

        TagDAO tagDAO = new TagDAO(getApplicationContext());
        tagDAO.inserirTag(tag);
        tagDAO.close();

        Toast.makeText(getApplicationContext(), "Salvo com sucesso!", Toast.LENGTH_SHORT).show();

        finish();
    }
}
