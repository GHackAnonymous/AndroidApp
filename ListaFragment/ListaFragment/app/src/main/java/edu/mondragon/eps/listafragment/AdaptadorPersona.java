package edu.mondragon.eps.listafragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by txperez on 09/03/2016.
 */
public class AdaptadorPersona extends ArrayAdapter<Persona> {
    Context contexto;
    int recurso;
    ArrayList<Persona> lista;

    public AdaptadorPersona(Context context, int resource,
                            ArrayList<Persona> personas) {
        super(context, resource,  personas);
        this.contexto = context;
        this.recurso = resource;
        this.lista = personas;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TableLayout vActual = (TableLayout) convertView;

        if (vActual ==null){
            vActual = new TableLayout(contexto);
            LayoutInflater inflater =(LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate (recurso,vActual);
        }
        TextView nombre = (TextView)  vActual.findViewById(R.id.texNombre);
        TextView apellido = (TextView) vActual.findViewById(R.id.textApellido);
        TextView tlfno = (TextView)  vActual.findViewById(R.id.textTelefono);
        TextView nota = (TextView)  vActual.findViewById(R.id.textNota);

        Persona persona = lista.get(position);
        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());
        tlfno.setText(persona.getTeléfono());
        nota.setText(persona.getNota());

        return vActual;

    }



}