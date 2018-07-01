package com.example.user.androidscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class menuUsuarioActivity extends AppCompatActivity {
    private String idUs;
    private String idRol;
    private String nomU;
    private String nomR;
    Button btnCerrar;
    Button btnTareaUs;
    TextView bien;
    TextView bienRol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);
        idUs = getIntent().getStringExtra("id");
        idRol = getIntent().getStringExtra("rol");
        nomU = getIntent().getStringExtra("nomUs");
        nomR = getIntent().getStringExtra("nomRol");
        btnTareaUs = (Button) findViewById(R.id.btnVerMiTarea);
        btnCerrar = (Button) findViewById(R.id.btnCerrarS);
        bien = (TextView) findViewById(R.id.cajaBien);
        bienRol = (TextView) findViewById(R.id.cajaBienRol);
        bien.setText("bienvenido : "+ nomU);
        bienRol.setText("Tu rol es :" + nomR);

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
