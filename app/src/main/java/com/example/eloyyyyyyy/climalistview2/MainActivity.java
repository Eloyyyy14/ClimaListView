package com.example.eloyyyyyyy.climalistview2;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvClima;
    TextView tv1;
    private static final String url="http://samples.openweathermap.org/data/2.5/forecast?id=6359373&APPID=56ff4d95b3bcb69e12bbeab1c3b9b8";
    ArrayList<DatosMeteorologicos> listaDatos=new ArrayList<DatosMeteorologicos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvClima=(ListView)findViewById(R.id.lvClima);
        tv1=(TextView)findViewById(R.id.tv1);

        RequestQueue request= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject jsonObject=new JSONObject(response.toString(0));
                    JSONArray JSONList=jsonObject.getJSONArray("list");

                    for(int i=0; i<JSONList.length(); i++){
                        int id=i;

                        String temperatura = JSONList.getJSONObject(i).getJSONObject("main").getString("temp");
                        String presion = JSONList.getJSONObject(i).getJSONObject("main").getString("pressure");
                        String humedad = JSONList.getJSONObject(i).getJSONObject("main").getString("humidity");
                        String nubosidad = JSONList.getJSONObject(i).getJSONObject("clouds").getString("all");

                        String icono = JSONList.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("icon");
                        String aspecto = JSONList.getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("main");

                        String velocidadViento = JSONList.getJSONObject(i).getJSONObject("wind").getString("speed");
                        String direccionViento = JSONList.getJSONObject(i).getJSONObject("wind").getString("deg");

                        String fechaHora = JSONList.getJSONObject(i).getString("dt_txt");

                        DatosMeteorologicos registro=new DatosMeteorologicos(id, temperatura, presion, humedad, nubosidad, icono, velocidadViento, direccionViento, aspecto, fechaHora);


                        listaDatos.add(registro);
                    }

                    AdaptadorClima miAdaptador=new AdaptadorClima(getApplicationContext(), listaDatos);

                    lvClima.setAdapter(miAdaptador);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        request.add(jsonObjectRequest);



        lvClima.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent paso = new Intent(getApplicationContext(), DetalleClima.class);
                startActivity(paso);
            }
        });

    }
}
