package com.example.user.androidscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuUsuarioActivity extends AppCompatActivity {
    private String idUs;
    private String idRol;
    Button btnCerrar;
    Button btnTareaUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        idUs = getIntent().getStringExtra("id");
        idRol = getIntent().getStringExtra("rol");
        btnTareaUs = (Button) findViewById(R.id.btnVerMiTarea);
        btnCerrar = (Button) findViewById(R.id.btnCerrarS);
        btnTareaUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TareaUs();
            }
        });
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });

    }
    public void TareaUs (){
        Intent sprint = new Intent(this, tareaUsuario.class);
        sprint.putExtra("idUs", idUs);
        startActivity(sprint);
    }


    public void cerrarSesion(){
        Intent login = new Intent(this, loginActivity.class);
        startActivity(login);
        finish();
    }
}
