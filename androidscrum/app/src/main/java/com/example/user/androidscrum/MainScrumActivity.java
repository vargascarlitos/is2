package com.example.user.androidscrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainScrumActivity extends AppCompatActivity {
    idUs = getIntent().getStringExtra("id");
    idRol = getIntent().getStringExtra("rol");
    nomU = getIntent().getStringExtra("nomUs");
    nomR = getIntent().getStringExtra("nomRol");
    btnTareaUs = (Button) findViewById(R.id.btnVerMiTarea);
    btnCerrar = (Button) findViewById(R.id.btnCerrarS);
    btnConf = (Button) findViewById(R.id.btnConfigUs);
    bien = (TextView) findViewById(R.id.cajaBien);
    bienRol = (TextView) findViewById(R.id.cajaBienRol);
        bien.setText("bienvenido : "+ nomU);
        bienRol.setText("Tu rol es :" + nomR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scrum);
    }
}
