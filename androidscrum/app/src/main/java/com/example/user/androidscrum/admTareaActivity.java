package com.example.user.androidscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admTareaActivity extends AppCompatActivity {

    Button btnDeletetarea;
    Button btnEdittarea;
    Button btnAddtarea;
    String idE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_tarea);
        idE = getIntent().getStringExtra("idE");
        btnDeletetarea = (Button) findViewById(R.id.btnEliminarTarea);
        btnEdittarea = (Button) findViewById(R.id.btnModificarTarea);
        btnAddtarea = (Button) findViewById(R.id.btnAgregarTarea);
        btnDeletetarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteT();
            }
        });
        btnEdittarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editT();
            }
        });
        btnAddtarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addT();
            }
        });
    }
    public void deleteT (){
        Intent tarea = new Intent(this, eliminarTareaActivity.class);
        tarea.putExtra("idE",idE );
        startActivity(tarea);
    }
    public void editT (){
        Intent sprint = new Intent(this, modificarTareaActivity.class);
        sprint.putExtra("idE",idE );
        startActivity(sprint);
    }
    public void addT (){
        Intent Asprint = new Intent(this,addTareaActivity.class);
        startActivity(Asprint);
    }
}
