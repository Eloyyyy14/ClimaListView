package com.example.eloyyyyyyy.climalistview2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eloyyyyyyy on 20/02/2018.
 */

public class AdaptadorClima extends BaseAdapter {
    Context contexto; //contexto de la aplicacion
    ArrayList<DatosMeteorologicos> Datos; //lista de datos a generar. Podemos usar tb un ArrayList

    public AdaptadorClima(Context contexto, ArrayList<DatosMeteorologicos> datos) {
        this.contexto = contexto;
        Datos = datos;
    }

    @Override
    public int getCount() {
        return Datos.size();
    }

    @Override
    public Object getItem(int i) { //Devuelve el objeto de la lista en la posición indicada como parametro
        return Datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //Es el método que se ejecuta cuando se muestra en mi ListView cada item
        View vista=view;
        LayoutInflater inflate = LayoutInflater.from(contexto); //Obtenemos el contexto del item sobre el cual estamos trabajando del ListView
        vista=inflate.inflate(R.layout.itemlistview_clima, null); //Consigo referenciar a la vista en sí

        TextView imgvIcono=(TextView)vista.findViewById(R.id.imgvIcono);
        TextView tvTemperatura=(TextView)vista.findViewById(R.id.tvTemperatura);
        TextView tvHora=(TextView)vista.findViewById(R.id.tvHora);


        imgvIcono.setText(Datos.get(i).getIcono());
        tvTemperatura.setText(Datos.get(i).getTemperatura());
        tvHora.setText(Datos.get(i).getFechaHora());

        return vista;

    }

}