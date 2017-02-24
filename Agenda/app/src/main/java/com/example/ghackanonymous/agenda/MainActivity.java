package com.example.ghackanonymous.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listaPersonas;
    List<Persona> agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agenda = new ArrayList<>();
        inicializar();
        listaPersonas = (ListView) this.findViewById(R.id.listapersona);

        AdaptadorPersona adaptador = new AdaptadorPersona(this,R.layout.personalayout,agenda);
        listaPersonas.setAdapter(adaptador);
        listaPersonas.setOnItemClickListener(this);
    }
    public void inicializar(){
        Persona persona = new Persona("Eder", "gomez", "4444444444");
        agenda.add(persona);
        persona = new Persona("eeee", "rrrr", "5555555555");
        agenda.add(persona);
        persona = new Persona("ttttt", "rfgtyh", "6666666666");
        agenda.add(persona);
        persona = new Persona("qwerty", "sderfcv", "7777777777");
        agenda.add(persona);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "llamando a: "+agenda.get(position).getNumeroTfn(), Toast.LENGTH_SHORT);
    }
}
