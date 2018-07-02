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

public class addUsuarioActivity extends AppCompatActivity {
    EditText cajaIdusuario;
    EditText cajaNomusuario;
    EditText cajaApeusuario;
    EditText cajaCorreousuario;
    EditText cajaIdEusuario;
    EditText cajaIdRusuario;
    EditText cajaPwdusuario;
    EditText cajaCedusuario;
    Button btnAddUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuario);
        cajaIdusuario = (EditText) findViewById(R.id.txtnewIDusuario);
        cajaNomusuario = (EditText) findViewById(R.id.txtnewNombreusuario);
        cajaApeusuario = (EditText) findViewById(R.id.txtnewApellido);
        cajaCorreousuario = (EditText) findViewById(R.id.txtnewCorreousuario);
        cajaIdEusuario = (EditText) findViewById(R.id.txtnewIDEusuario);
        cajaIdRusuario = (EditText) findViewById(R.id.txtnewIdRolusuario);
        cajaPwdusuario = (EditText) findViewById(R.id.txtnewpwdusuario);
        cajaCedusuario = (EditText) findViewById(R.id.txtcedusuario);
        btnAddUs = (Button) findViewById(R.id.btnaddUsuario);
        btnAddUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarUsuario();
            }
        });
    }
    public void agregarUsuario() {
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.usuario/agregarusuario";
        String idusuario = cajaIdusuario.getText().toString();
        String nombre = cajaNomusuario.getText().toString();
        String apellido = cajaApeusuario.getText().toString();
        String correo = cajaCorreousuario.getText().toString();
        String idequipo = cajaIdEusuario.getText().toString();
        String idrol = cajaIdRusuario.getText().toString();
        String contrasenha = cajaPwdusuario.getText().toString();
        String cedula = cajaCedusuario.getText().toString();


        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idusuario", idusuario);
            json.put("nombre", nombre);
            json.put("apellido", apellido);
            json.put("correo", correo);
            json.put("idequipo", idequipo);
            json.put("idrol", idrol);
            json.put("contrasenha", contrasenha);
            json.put("cedula", cedula);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(addUsuarioActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addUsuarioActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
