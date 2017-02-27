package edu.mondragon.eps.listafragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by txperez on 10/03/2016.
 */
public class PersonaFragment extends Fragment implements View.OnClickListener {
    final String LOGTAG = "frag";
    Button bOk,bCancel;
    EditText edNombre,edApellido,edTelefono,edNota;
    Persona persona = null;
    OnNewPersonAdded activityCallBack;
    boolean edit= false;
    Persona personaEditada;

    public interface OnNewPersonAdded {
        public void onNewPersonCreated(Persona persona);
        public void onActionCanceled();
        public void onPersonChanged();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(LOGTAG, "creando view");
        View view =   inflater.inflate(R.layout.datospersona, container, false);
        edNombre = (EditText) view.findViewById(R.id.edNombre);
        edApellido = (EditText)view.findViewById(R.id.edApellido);
        edTelefono = (EditText)view.findViewById(R.id.edTlfno);
        edNota = (EditText)view.findViewById(R.id.edNotan);

        bOk = (Button) view.findViewById(R.id.bOk);
        bOk.setOnClickListener(this);
        bCancel = (Button) view.findViewById(R.id.bCancel);
        bCancel.setOnClickListener(this);
        Log.i(LOGTAG,"editando: " + edit);
        if (edit) {
            edNombre.setText(personaEditada.getNombre());
            edApellido.setText(personaEditada.getApellido());
            edTelefono.setText(personaEditada.getTeléfono());
            edNota.setText(personaEditada.getNota());
        }

        return view;
   }

    @Override
    public void onAttach(Context context) {
        Log.i(LOGTAG, "ATTACHED 23");
        super.onAttach(context);
        onAttachContext(context);
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i(LOGTAG, "ATTACHED 21");
        super.onAttach(activity);
        onAttachContext(activity);
    }
    protected void onAttachContext(Context context){
        try {
            Log.i(LOGTAG,"conectado");
            activityCallBack = (OnNewPersonAdded) context;
        }catch (ClassCastException e) {
            Log.i(LOGTAG,"excepción");
            throw new ClassCastException(context.toString() + " must implement OnNewPersonAdded");
        }
    }
    public void edit (Persona persona){
        Log.i(LOGTAG,"En modo edición");
        edit = true;
        personaEditada = persona;
    }
    @Override
    public void onClick(View v) {
        if (v==bOk){
            Log.i(LOGTAG, "clikado");
            if (!edit){
                persona = new Persona (edNombre.getText().toString(),edApellido.getText().toString(),
                        edTelefono.getText().toString(),edNota.getText().toString());
                activityCallBack.onNewPersonCreated(persona);
                Log.i(LOGTAG, "pasada persona");
            }else{
                personaEditada.update(edNombre.getText().toString(),edApellido.getText().toString(),
                        edTelefono.getText().toString(),edNota.getText().toString());
                activityCallBack.onPersonChanged();
                Log.i(LOGTAG,"persona cambiada");
            }

        }else{
            activityCallBack.onActionCanceled();
        }
    }
}
