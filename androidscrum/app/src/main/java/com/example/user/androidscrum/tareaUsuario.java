package com.example.user.androidscrum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class tareaUsuario extends AppCompatActivity {
    ListView listV;
    RequestQueue rq;
    StringRequest peticion;
    private String idUs;
    private ArrayList<String> v_tareaNombre;
    private ArrayList<Integer> v_idTarea;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea_usuario);
        listV = (ListView)findViewById(R.id.lvTareaUs);
        idUs = getIntent().getStringExtra("idUs");
        v_tareaNombre = new ArrayList<String>();
        v_idTarea = new ArrayList<Integer>();
        adapter = new ArrayAdapter<String>(tareaUsuario.this, R.layout.list_usuario, v_tareaNombre);
        listV.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        listarSprint();
    }

    public void listarSprint(){
        String URL = "http://169.254.118.241:8080/proyecto/webresources/pck_entidades.tarea/listartarea/"+idUs;
                //path + "sprint/listar/miembro/"+idUser;
        rq = Volley.newRequestQueue(this);
        adapter.clear();
        v_idTarea.clear();
        v_tareaNombre.clear();
        peticion = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                    for (int i=0;i<=jsonArray.length();i++){
                        JSONObject json = new JSONObject(jsonArray.get(i).toString());
                        v_idTarea.add(Integer.parseInt(json.getString("idtarea")));
                        v_tareaNombre.add(json.getString("nombre"));
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(tareaUsuario.this, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(peticion);
    }
}
