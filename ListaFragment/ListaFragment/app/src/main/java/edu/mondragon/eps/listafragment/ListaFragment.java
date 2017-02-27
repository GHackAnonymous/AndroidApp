package edu.mondragon.eps.listafragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * Created by txperez on 10/03/2016.
 */
public class ListaFragment extends Fragment implements AdapterView.OnItemLongClickListener {
    AdaptadorPersona adaptador;
    ArrayList<Persona> agenda;
    ListView viewLista;
    final String LOGTAG = "frag";
    AccionListaListener listener;
    int selectedPosition = -1;



    public interface AccionListaListener {
        public void onDelete (ListaFragment lista, int posicion, Persona p);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        agenda = new ArrayList<>();
        inicializar();
        Log.i(LOGTAG, "Creando fragmento lista");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOGTAG,"Creando view fragmento lista");
        return   inflater.inflate(R.layout.listafragment, container, false);
   }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout layout = (LinearLayout) view;
        viewLista = (ListView)layout.findViewById(R.id.listPersonas);
        viewLista.setSelector(R.drawable.selector);
        //viewLista.setOnItemLongClickListener(this);
        viewLista.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        viewLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                viewLista.requestFocusFromTouch();
                viewLista.setSelection(position);
                Log.i(LOGTAG, "persona clicada " + position + "," + viewLista.getSelectedItemPosition());
                Toast.makeText((Context)listener,"clickado",Toast.LENGTH_SHORT).show();
                ListaFragment.this.selectedPosition = viewLista.getSelectedItemPosition();
            }
        });
        adaptador = new AdaptadorPersona(this.getActivity().getBaseContext(),R.layout.persona,agenda);

        viewLista.setAdapter(adaptador);
        Log.i(LOGTAG, "vista fragmento lista creada");
    }
    public int getSelectedPosition(){
        Log.i(LOGTAG, "posicion seleccionada " + selectedPosition+ "," + viewLista.getSelectedItemPosition());
        return selectedPosition;
    }
    public Persona getSelectedPerson(){
        if (selectedPosition==-1) return null;
        return agenda.get(selectedPosition);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachContext(context);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onAttachContext(activity);
    }
    protected void onAttachContext(Context context){
        try {
            listener = (AccionListaListener) context;
        }catch (ClassCastException e) {

            throw new ClassCastException(context.toString() + " must implement OnpcionDialogoListener");
        }
    }

    private void inicializar() {
        Persona persona = new Persona ("Txema","Perez","678345678","Profe");
        agenda.add(persona);
        persona = new Persona ("Xabier","Elkoro","678324778","Profe");
        agenda.add(persona);
        persona = new Persona ("Julen","Izeta","609345678","Clase");
        agenda.add(persona);
        persona = new Persona ("Ekaitz","Irusta","678345678","Clase");
        agenda.add(persona);
    }

    public ListView getListView(){
        return viewLista;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        borrar(position);
        return true;
    }
    public void borrar(int position){

        listener.onDelete(this, position, agenda.get(position));
    }
    public void add(Persona persona){
        Log.i(LOGTAG, "persona Recibida en lista y añadida");
        agenda.add(persona);
        adaptador.notifyDataSetChanged();

    }
    public void update(){
        adaptador.notifyDataSetChanged();
    }
    public void delete(int posicion) {
        agenda.remove(posicion);
        adaptador.notifyDataSetChanged();
    }

}
