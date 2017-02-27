package edu.mondragon.eps.listafragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by txperez on 13/03/2016.
 */
public class BorrarDialogFragment extends DialogFragment {

    public interface OpcionDialogoListener{
        public void onDialogPositiveClick (DialogFragment dialogo);
        public void onDialogNegativeClick (DialogFragment dialogo);
    }
    OpcionDialogoListener listener;
    Persona persona;

    public void setPersona (Persona p){
        this.persona = p;
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
            listener = (OpcionDialogoListener) context;
        }catch (ClassCastException e) {

            throw new ClassCastException(context.toString() + " must implement OnpcionDialogoListener");
        }
    }
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String mensaje = this.getString(R.string.mensajedialogoborrar)+ " "+persona.getNombre();
        builder.setTitle(R.string.titulodialogoborrar)
                .setMessage(mensaje )
                .setPositiveButton(R.string.botonOkdialogoborrar,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDialogPositiveClick(BorrarDialogFragment.this);

                            }
                        })
                .setNegativeButton(R.string.botonCanceldialogoborrar,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDialogNegativeClick(BorrarDialogFragment.this);

                            }
                        });
        return builder.create();
    }


}