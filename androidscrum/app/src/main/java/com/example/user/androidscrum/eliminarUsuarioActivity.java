package com.example.user.androidscrum;

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

public class eliminarUsuarioActivity extends AppCompatActivity {
    ListView listUsEliminar;
    RequestQueue rq;
    StringRequest peticion;
    private String idE;
    private ArrayList<String> v_Nus;
    private ArrayList<Integer> v_idU;
    private ArrayAdapter<String> adapter;
    private String IDu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_usuario);
        listUsEliminar = (ListView)findViewById(R.id.lvEliminarUsuario);
        idE = getIntent().getStringExtra("idE");
        v_Nus = new ArrayList<String>();
        v_idU = new ArrayList<Integer>();
        adapter = new ArrayAdapter<String>(eliminarUsuarioActivity.this, R.layout.list_usuario, v_Nus);
        listUsEliminar.setAdapter(adapter);
        listUsEliminar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IDu = v_idU.get(i).toString();
                eliminarusuario();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        listarUs();
    }

    public void listarUs(){
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.usuario/listarus/"+idE;
        rq = Volley.newRequestQueue(this);
        adapter.clear();
        v_idU.clear();
        v_Nus.clear();
        peticion = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++){
                        JSONObject json = new JSONObject(jsonArray.get(i).toString());
                        v_idU.add(Integer.parseInt(json.getString("idusuario")));
                        v_Nus.add(json.getString("nombre"));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(eliminarUsuarioActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(peticion);
    }
    public void eliminarusuario() {
        String URL =  "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.usuario/eliminarus/"+IDu;
        RequestQueue queue = Volley.newRequestQueue(this);
        JSONObject json = new JSONObject();
        try {
            json.put("idusuario", IDu);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest solicitud = new JsonObjectRequest(Request.Method.GET, URL, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("respuesta")){
                        Toast.makeText(eliminarUsuarioActivity.this, "Eliminado", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(eliminarUsuarioActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(solicitud);
    }
}
