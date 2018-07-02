package com.example.user.androidscrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class leerModificarUsuarioActivity extends AppCompatActivity {
    EditText cajaN;
    EditText cajaA;
    EditText cajaC;
    EditText cajaI;
    Button btnMu;
    String idUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_modificar_usuario);
        cajaN= (EditText) findViewById(R.id.txtN);
        cajaA= (EditText) findViewById(R.id.txtA);
        cajaC= (EditText) findViewById(R.id.txtC);
        cajaI= (EditText) findViewById(R.id.txtIR);
        btnMu= (Button) findViewById(R.id.btnMo);
        idUs = getIntent().getStringExtra("idUs");
        btnMu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modificarUsu();
            }
        });
    }
    public void modificarUsu() {
        String URL =  "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.usuario/editarusuarioxscrum";
        String nombre = cajaN.getText().toString();
        String apellido = cajaA.getText().toString();
        String correo = cajaC.getText().toString();
        String idrol = cajaI.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idusuario", idUs);
            json.put("nombre", nombre);
            json.put("apellido", apellido);
            json.put("correo", correo);
            json.put("idrol", idrol);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.PUT, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(leerModificarUsuarioActivity.this, " actualizado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(leerModificarUsuarioActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
