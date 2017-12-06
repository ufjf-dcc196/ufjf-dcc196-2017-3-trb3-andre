package com.example.andre.trb3.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.example.andre.trb3.R;
import com.example.andre.trb3.dbhelper.TagDAO;
import com.example.andre.trb3.objects.Tag;
import java.util.ArrayList;

public class Tags extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);

        Button btnCadastrarTag = (Button) findViewById(R.id.btn_cadastrar_tag);
        Button btnAtribuirTag = (Button) findViewById(R.id.btn_atribuir_tags);

        startActivityOnClick(btnCadastrarTag, CadastrarTag.class);
        startActivityOnClick(btnAtribuirTag, AtribuirTags.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

        TagDAO tagDAO = new TagDAO(getApplicationContext());
        ArrayList<Tag> tags = tagDAO.buscarTags();
        tagDAO.close();

        ListView listaTags = (ListView) findViewById(R.id.lv_tags);
        ArrayAdapter<Tag> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tags);
        listaTags.setAdapter(adapter);

        listaTags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tag tag = (Tag) parent.getItemAtPosition(position);
                Intent intent = new Intent(Tags.this, TarefasAssociadas.class);
                intent.putExtra("TAG", tag);
                startActivity(intent);
            }
        });
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
}
