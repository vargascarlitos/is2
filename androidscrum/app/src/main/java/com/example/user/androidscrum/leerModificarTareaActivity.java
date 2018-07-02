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

public class leerModificarTareaActivity extends AppCompatActivity {
    EditText cajaNomT;
    EditText cajafIni;
    EditText cajafFin;
    EditText cajaEstado;
    Button btnModi;
    String idSprint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_modificar_tarea);
        cajaNomT= (EditText) findViewById(R.id.txtnewNombreT);
        cajafIni= (EditText) findViewById(R.id.txtnewFechaI);
        cajafFin= (EditText) findViewById(R.id.txtnewFechaF);
        cajaEstado= (EditText) findViewById(R.id.txtnewEstado);
        btnModi= (Button) findViewById(R.id.btnModificarTarea);
        idSprint = getIntent().getStringExtra("idSprint");
        btnModi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modificarSprint();
            }
        });
    }
    public void modificarSprint() {
        String URL =  "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.sprint/editarsprint";
        String nombre = cajaNomT.getText().toString();
        String fechaini = cajafIni.getText().toString();
        String fechafin = cajafFin.getText().toString();
        String estado = cajaEstado.getText().toString();
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idsprint", idSprint);
            json.put("nombre", nombre);
            json.put("fechaini", fechaini+"T00:00:00-03:00");
            json.put("fechafin", fechafin+"T00:00:00-03:00");
            json.put("estado", estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.PUT, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(leerModificarTareaActivity.this, " actualizado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(leerModificarTareaActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
