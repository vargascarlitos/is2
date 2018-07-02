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

public class modificarTareaActivity extends AppCompatActivity {
    ListView listTareaModificar;
    RequestQueue rq;
    StringRequest peticion;
    private String idE;
    private ArrayList<String> v_sprintN;
    private ArrayList<Integer> v_idS;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_tarea);
        listTareaModificar = (ListView)findViewById(R.id.lvModificartarea);
        idE = getIntent().getStringExtra("idE");
        v_sprintN = new ArrayList<String>();
        v_idS = new ArrayList<Integer>();
        adapter = new ArrayAdapter<String>(modificarTareaActivity.this, R.layout.list_usuario, v_sprintN);
        listTareaModificar.setAdapter(adapter);
        listTareaModificar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent dato = new Intent(modificarTareaActivity.this, leerModificarTareaActivity.class);
                dato.putExtra("idSprint", v_idS.get(i).toString());
                startActivity(dato);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        listarS();
    }

    public void listarS(){
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.sprint/listarsprint/"+idE;
        rq = Volley.newRequestQueue(this);
        adapter.clear();
        v_idS.clear();
        v_sprintN.clear();
        peticion = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++){
                        JSONObject json = new JSONObject(jsonArray.get(i).toString());
                        v_idS.add(Integer.parseInt(json.getString("idsprint")));
                        v_sprintN.add(json.getString("nombre"));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(modificarTareaActivity.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(peticion);
    }
}
