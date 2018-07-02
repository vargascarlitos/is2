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

public class addProyectoActivity extends AppCompatActivity {
    EditText cajaIDproyecto;
    EditText cajaNombreProyecto;
    EditText cajaDescripProyecto;
    EditText cajaFiniProyecto;
    EditText cajaFfinProyecto;
    EditText cajaEstadoProyecto;
    Button btnaddProyecto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_proyecto);
        cajaIDproyecto = (EditText) findViewById(R.id.txtnewIDpro);
        cajaNombreProyecto = (EditText) findViewById(R.id.txtnewNpro);
        cajaDescripProyecto = (EditText) findViewById(R.id.txtnewDESC);
        cajaFiniProyecto = (EditText) findViewById(R.id.txtnewFinipro);
        cajaFfinProyecto = (EditText) findViewById(R.id.txtnewFfinpro);
        cajaEstadoProyecto = (EditText) findViewById(R.id.txtnewDESC);
        btnaddProyecto = (Button) findViewById(R.id.btnAddPro);

        btnaddProyecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agregarProyecto();
            }
        });

    }
    public void agregarProyecto() {
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.proyecto/agregarproyecto";
        String idproyecto = cajaIDproyecto.getText().toString();
        String nombre = cajaNombreProyecto.getText().toString();
        String descripcion = cajaDescripProyecto.getText().toString();
        String fechaini = cajaFiniProyecto.getText().toString();
        String fechafin = cajaFfinProyecto.getText().toString();
        String estado = cajaEstadoProyecto.getText().toString();

        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idproyecto", idproyecto);
            json.put("nombre", nombre);
            json.put("descripcion", descripcion);
            json.put("fechaini", fechaini+"T00:00:00-04:00");
            json.put("fechafin", fechafin+"T00:00:00-04:00");
            json.put("estado", estado);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.POST, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(addProyectoActivity.this, "Proyecto creado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addProyectoActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
