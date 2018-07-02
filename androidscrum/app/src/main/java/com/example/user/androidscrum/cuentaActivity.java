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

public class cuentaActivity extends AppCompatActivity {
    EditText cajaNom;
    EditText cajaApe;
    EditText cajaCo;
    EditText cajaCon;
    Button btnModUs;
    String idUs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        cajaNom= (EditText) findViewById(R.id.txtnewNom);
        cajaApe= (EditText) findViewById(R.id.txtnewApe);
        cajaCo= (EditText) findViewById(R.id.txtnewCorreo);
        cajaCon= (EditText) findViewById(R.id.txtnewCon);
        btnModUs= (Button) findViewById(R.id.btnModUs);
        idUs = getIntent().getStringExtra("idUs");
        btnModUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modificarUsuario();
            }
        });
    }
    public void modificarUsuario() {
        String URL =  "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.usuario/editarusuario";
        String nombre = cajaNom.getText().toString();
        String apellido = cajaApe.getText().toString();
        String correo = cajaCo.getText().toString();
        String contrasenha = cajaCon.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idusuario", idUs);
            json.put("nombre", nombre);
            json.put("apellido", apellido);
            json.put("correo", correo);
            json.put("contrasenha", contrasenha);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.PUT, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(cuentaActivity.this, "Cuenta actualizada", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(cuentaActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
