package com.example.user.androidscrum;

import android.content.Intent;
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
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static java.security.AccessController.getContext;
public class loginActivity extends AppCompatActivity  implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue rq;
    JsonRequest peticion;
    EditText cajaUser;
    EditText cajaPwd;
    Button btnConsulta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cajaUser = (EditText) findViewById(R.id.txtUser);
        cajaPwd = (EditText) findViewById(R.id.txtPwd);
        btnConsulta = (Button) findViewById(R.id.btn);
        rq = Volley.newRequestQueue(getApplicationContext());
        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                iniciarSesion();
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se encontro " + error.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        //Toast.makeText(getApplicationContext(),"Bienvenido" + cajaUser.getText().toString(),Toast.LENGTH_LONG).show();
        //Intent menu = new Intent(loginActivity.this, menuUsuarioActivity.class);
        //startActivity(menu);
        //finish();
       try {
           String nomUs = response.getString("nombre");
           String nomRol = response.getString("nombrerol");
           String id = response.getString("idusuario");
           int rol = response.getInt("idrol");
               Toast.makeText(loginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                Intent menu = new Intent();
                if (rol == 2) {
                    menu = new Intent(loginActivity.this, menuUsuarioActivity.class);
                } else if (rol == 1) {
                    menu = new Intent(loginActivity.this, MainScrumActivity.class);
                }
                menu.putExtra("nomUs", nomUs);
                menu.putExtra("nomRol", nomRol);
                menu.putExtra("id", id);
                menu.putExtra("rol", rol + "");
                startActivity(menu);
                finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    private void iniciarSesion(){
        String URL = "http://192.168.1.51:14606/proyecto/webresources/pck_entidades.usuario/login";
        String us = cajaUser.getText().toString();
        String pq = cajaPwd.getText().toString();
        JSONObject json = new JSONObject();
        try {
            json.put("cedula", us);
            json.put("contrasenha", pq);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        peticion = new JsonObjectRequest(Request.Method.POST,URL,json,this, this);
        rq.add(peticion);
    }
}
