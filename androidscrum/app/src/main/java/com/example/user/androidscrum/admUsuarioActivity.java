package com.example.user.androidscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admUsuarioActivity extends AppCompatActivity {
    Button btnDeleteUs;
    Button btnEditUs;
    Button btnAddUs;
    String idE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_usuario);
        idE = getIntent().getStringExtra("idE");
        btnDeleteUs = (Button) findViewById(R.id.btnEliminarUs);
        btnEditUs = (Button) findViewById(R.id.btnModificarUs);
        btnAddUs = (Button) findViewById(R.id.btnAgregarUs);
        btnDeleteUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteU();
            }
        });
        btnEditUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editU();
            }
        });
        btnAddUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addU();
            }
        });


    }
    public void deleteU (){
        Intent dus = new Intent(this, eliminarUsuarioActivity.class);
        dus.putExtra("idE",idE );
        startActivity(dus);
    }
    public void editU (){
        Intent eus = new Intent(this, modificarUsuarioActivity.class);
        eus.putExtra("idE",idE );
        startActivity(eus);
    }
    public void addU (){
        Intent addu = new Intent(this,addUsuarioActivity.class);
        startActivity(addu);
    }
}
