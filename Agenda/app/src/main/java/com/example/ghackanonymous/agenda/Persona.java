package com.example.ghackanonymous.agenda;

/**
 * Created by GHackAnonymous on 17/02/2017.
 */

public class Persona {
    private String nombre;
    private String apellido;
    private String numeroTfn;

    public Persona(String nombre, String apellido, String numeroTfn) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTfn = numeroTfn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroTfn() {
        return numeroTfn;
    }

    public void setNumeroTfn(String numeroTfn) {
        this.numeroTfn = numeroTfn;
    }
}
