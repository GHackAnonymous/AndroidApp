package com.example.ghackanonymous.agenda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by GHackAnonymous on 17/02/2017.
 */

public class AdaptadorPersona extends ArrayAdapter<Persona>{

    Context contexto;
    int recurso;
    List<Persona> lista;

    public AdaptadorPersona(Context context, int resource, List<Persona> personas){
        super(context, resource,personas);

        this.contexto = context;
        this.recurso = resource;
        this.lista = personas;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TableLayout vActual = (TableLayout) convertView;

        if(vActual == null){
            vActual = new TableLayout(contexto);

            LayoutInflater inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(recurso, vActual);

        }

        TextView nombre = (TextView) vActual.findViewById(R.id.nombre);
        TextView apellido = (TextView) vActual.findViewById(R.id.apellido);
        TextView tlfno = (TextView) vActual.findViewById(R.id.numero);

        Persona persona = lista.get(position);
        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());
        tlfno.setText(persona.getNumeroTfn());


        return vActual;
    }
}
