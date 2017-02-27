package edu.mondragon.eps.listafragment;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
                    PersonaFragment.OnNewPersonAdded,BorrarDialogFragment.OpcionDialogoListener,
                    ListaFragment.AccionListaListener{



    final String LOGTAG = "frag";
    FloatingActionButton fab;
    FragmentManager fragManager;
    ListaFragment fragLista;
    PersonaFragment fragPersona;
    BorrarDialogFragment fragBorrar;
    Menu menu;
    int posicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = (FloatingActionButton) this.findViewById(R.id.fbAdd);
        fab.setOnClickListener(this);
        fragLista = new ListaFragment();
        fragManager = getFragmentManager();
        FragmentTransaction transacion = fragManager.beginTransaction();
        transacion.add(R.id.ventana,fragLista);
        transacion.commit();
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        fragPersona= new PersonaFragment();
        FragmentTransaction transacion = fragManager.beginTransaction();
        transacion.detach(fragLista);
        transacion.add(R.id.ventana, fragPersona);
        transacion.commit();
        fab.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onNewPersonCreated(Persona persona) {
        Log.i(LOGTAG, "persona recibida en main");

        FragmentTransaction transacion = fragManager.beginTransaction();
        transacion.remove(fragPersona);
        transacion.attach(fragLista);
        transacion.commit();
        fragLista.add(persona);
        fab.setVisibility(View.VISIBLE);
    }
    @Override
    public void onPersonChanged() {
        Log.i(LOGTAG,"Persona modificada");
        FragmentTransaction transacion = fragManager.beginTransaction();
        transacion.remove(fragPersona);
        transacion.attach(fragLista);
        transacion.commit();
        fragLista.update();
        fab.setVisibility(View.VISIBLE);
    }
    @Override
    public void onActionCanceled() {
        FragmentTransaction transacion = fragManager.beginTransaction();
        transacion.remove(fragPersona);
        transacion.attach(fragLista);
        transacion.commit();
        fab.setVisibility(View.VISIBLE);
    }



    @Override
    public void onDialogPositiveClick(DialogFragment dialogo) {
        fragLista.delete(posicion);
        fragBorrar.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogo) {
        fragBorrar.dismiss();
    }

    @Override
    public void onDelete(ListaFragment lista, int posicion, Persona p) {
        this.posicion = posicion;
        fragBorrar = new BorrarDialogFragment();
        fragBorrar.setPersona (p);
        fragBorrar.show(fragManager, "borrar");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transacion;
        switch (item.getItemId()){
            case R.id.op_add:
                fragPersona= new PersonaFragment();
                transacion = fragManager.beginTransaction();
                transacion.detach(fragLista);
                transacion.add(R.id.ventana, fragPersona);
                transacion.commit();
                this.fab.setVisibility (View.INVISIBLE);
                return true;
            case R.id.op_delete:

                fragLista.borrar(fragLista.getSelectedPosition());
                return true;
            case R.id.op_edit:
                fragPersona = new PersonaFragment();
                Persona persona = fragLista.getSelectedPerson();
                transacion = fragManager.beginTransaction();
                transacion.detach(fragLista);
                transacion.add(R.id.ventana, fragPersona);
                transacion.commit();
                Log.i(LOGTAG, "Editando: " + ((persona == null) ? "persona nula" : persona.getNombre()));
                fragPersona.edit(persona);
                this.fab.setVisibility(View.INVISIBLE);
                return true;
            default: return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (fragLista.isDetached()){
            MenuItem opcion = menu.findItem(R.id.op_add).setEnabled(false);
            opcion = menu.findItem(R.id.op_delete).setEnabled(false);
            opcion = menu.findItem(R.id.op_edit).setEnabled(false);
        }else if(fragLista.getSelectedPosition()==-1){
            MenuItem opcion = menu.findItem(R.id.op_add).setEnabled(true);
            opcion = menu.findItem(R.id.op_delete).setEnabled(false);
            opcion = menu.findItem(R.id.op_edit).setEnabled(false);
        }else{
            MenuItem opcion = menu.findItem(R.id.op_add).setEnabled(true);
            opcion = menu.findItem(R.id.op_delete).setEnabled(true);
            opcion = menu.findItem(R.id.op_edit).setEnabled(true);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
}
