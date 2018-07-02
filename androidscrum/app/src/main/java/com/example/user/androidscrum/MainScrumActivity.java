package com.example.user.androidscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScrumActivity extends AppCompatActivity {
    private String idUs;
    private String idRol;
    private String nomU;
    private String nomR;
    private String idE;
    Button btnAdnt;
    Button btnAdmU;
    Button btnMcuenta;
    Button btnCrearP;
    Button btnCerrar;
    TextView bien;
    TextView bienRol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrum);
        idUs = getIntent().getStringExtra("id");
        idRol = getIntent().getStringExtra("rol");
        idE = getIntent().getStringExtra("idE");
        nomU = getIntent().getStringExtra("nomUs");
        nomR = getIntent().getStringExtra("nomRol");
        btnAdnt = (Button) findViewById(R.id.btnAdmTarea);
        btnAdmU = (Button) findViewById(R.id.btnAdmUs);
        btnMcuenta = (Button) findViewById(R.id.btnModCuenta);
        btnCrearP = (Button) findViewById(R.id.btnCrearPro);
        btnCerrar = (Button) findViewById(R.id.btnCerrarSe);
        bien = (TextView) findViewById(R.id.cajaBien);
        bienRol = (TextView) findViewById(R.id.cajaBienRol);
        bien.setText("bienvenido : "+ nomU);
        bienRol.setText("Tu rol es :" + nomR);

        btnAdnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admtarea();
            }
        });
        btnAdmU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admusuario();
            }
        });
        btnMcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigurarUsuario();
            }
        });

        btnCrearP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProyecto();
            }
        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
    }
    public void admtarea(){
        Intent admt = new Intent(this, admTareaActivity.class);
        admt.putExtra("idUs", idUs);
        admt.putExtra("idE", idE);
        startActivity(admt);
        finish();
    }
    public void admusuario(){
        Intent admt = new Intent(this, admUsuarioActivity.class);
        admt.putExtra("idUs", idUs);
        admt.putExtra("idE", idE);
        startActivity(admt);
        finish();
    }
    public void ConfigurarUsuario (){
        Intent cuenta = new Intent(this, cuentaActivity.class);
        cuenta.putExtra("idUs", idUs);
        startActivity(cuenta);
    }
    public void addProyecto (){
        Intent addP = new Intent(this, addProyectoActivity.class);
        startActivity(addP);
    }
    public void cerrarSesion(){
        Intent login = new Intent(this, loginActivity.class);
        startActivity(login);
        finish();
    }
}

