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

public class addTareaActivity extends AppCompatActivity {
    EditText cajaIdS;
    EditText cajaNomS;
    EditText cajafIniS;
    EditText cajafFinS;
    EditText cajaEstadoS;
    EditText cajaidUS;
    Button btnAddS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarea);
        cajaIdS = (EditText) findViewById(R.id.txtnewIdSprint);
        cajaNomS = (EditText) findViewById(R.id.txtnewNombreSprint);
        cajafIniS = (EditText) findViewById(R.id.txtnewFechaIni);
        cajafFinS = (EditText) findViewById(R.id.txtnewFechaFin);
        cajaEstadoS = (EditText) findViewById(R.id.txtnewEstadoSprint);
        cajaidUS = (EditText) findViewById(R.id.txtnewIdUs);
        btnAddS = (Button) findViewById(R.id.btnaddTarea);
        btnAddS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarTarea();
            }
        });
    }

    public void agregarTarea() {
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.sprint/agregarsprint";
        String idsprint = cajaIdS.getText().toString();
        String nombre = cajaNomS.getText().toString();
        String fechaini = cajafIniS.getText().toString();
        String fechafin = cajafFinS.getText().toString();
        String estado = cajaEstadoS.getText().toString();
        String idus = cajaidUS.getText().toString();


        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idsprint", idsprint);
            json.put("nombre", nombre);
            json.put("fechaini", fechaini+"T00:00:00-04:00");
            json.put("fechafin", fechafin+"T00:00:00-04:00");
            json.put("estado", estado);
            json.put("idus", idus);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(addTareaActivity.this, "Tarea creado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addTareaActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
    }


