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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class modificarUsuarioActivity extends AppCompatActivity {
    ListView listUsModif;
    RequestQueue rq;
    StringRequest peticion;
    private String idE;
    private ArrayList<String> v_Nus;
    private ArrayList<Integer> v_idU;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);
        listUsModif = (ListView)findViewById(R.id.lvModificarUsuario);
        idE = getIntent().getStringExtra("idE");
        v_Nus = new ArrayList<String>();
        v_idU = new ArrayList<Integer>();
        adapter = new ArrayAdapter<String>(modificarUsuarioActivity.this, R.layout.list_usuario, v_Nus);
        listUsModif.setAdapter(adapter);
        listUsModif.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent dato = new Intent(modificarUsuarioActivity.this, leerModificarUsuarioActivity.class);
                dato.putExtra("idUs", v_idU.get(i).toString());
                startActivity(dato);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        listarU();
    }

    public void listarU(){
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
                Toast.makeText(modificarUsuarioActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(peticion);
    }
    }
