package com.example.user.androidscrum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class eliminarTareaActivity extends AppCompatActivity {
    ListView listTareaEliminar;
    RequestQueue rq;
    StringRequest peticion;
    private String idE;
    private ArrayList<String> v_tareaN;
    private ArrayList<Integer> v_idT;
    private ArrayAdapter<String> adapter;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_tarea);
        listTareaEliminar = (ListView)findViewById(R.id.lvEliminartarea);
        idE = getIntent().getStringExtra("idE");
        v_tareaN = new ArrayList<String>();
        v_idT = new ArrayList<Integer>();
        adapter = new ArrayAdapter<String>(eliminarTareaActivity.this, R.layout.list_usuario, v_tareaN);
        listTareaEliminar.setAdapter(adapter);
        listTareaEliminar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ID = v_idT.get(i).toString();
                eliminartarea();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        listarT();
    }

    public void listarT(){
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.tarea/listartareascrum/"+idE;
        rq = Volley.newRequestQueue(this);
        adapter.clear();
        v_idT.clear();
        v_tareaN.clear();
        peticion = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++){
                        JSONObject json = new JSONObject(jsonArray.get(i).toString());
                        v_idT.add(Integer.parseInt(json.getString("idtarea")));
                        v_tareaN.add(json.getString("nombre"));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(eliminarTareaActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(peticion);
    }

    public void eliminartarea() {
        String URL =  "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.tarea/eliminartarea/"+ID;
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idtarea", ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.GET, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(eliminarTareaActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(eliminarTareaActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
