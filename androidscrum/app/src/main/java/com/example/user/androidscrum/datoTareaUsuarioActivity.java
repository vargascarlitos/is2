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

public class datoTareaUsuarioActivity extends AppCompatActivity {
    private EditText cajaEstadonew;
    private String id;
    private Button btnEstus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_tarea_usuario);
        cajaEstadonew= (EditText) findViewById(R.id.txtEstnew);
        btnEstus= (Button) findViewById(R.id.btnModifTaUs);
        id = getIntent().getStringExtra("id");
        btnEstus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modificarTareaUs();
            }
        });
    }

    public void modificarTareaUs() {
        String URL =  "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.usuario/editartareaus";
        String estado = cajaEstadonew.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idtarea", id);
            json.put("estado", estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.PUT, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(datoTareaUsuarioActivity.this, "Estado actualizado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(datoTareaUsuarioActivity.this, "ERROR"+ error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
