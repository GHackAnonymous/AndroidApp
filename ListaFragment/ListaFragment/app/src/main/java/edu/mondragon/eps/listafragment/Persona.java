package edu.mondragon.eps.listafragment;

/**
 * Created by txperez on 09/03/2016.
 */
public class Persona {
    String nombre;
    String apellido;
    String teléfono;
    String nota;

    public Persona(String nombre, String apellido, String teléfono, String nota){
        this.nombre = nombre;
        this.apellido = apellido;
        this.teléfono = teléfono;
        this.nota = nota;
    }
    public void update (String nombre, String apellido, String teléfono, String nota){
        if (!this.nombre.equals(nombre)){ this.nombre = nombre;}
        if (!this.apellido.equals(apellido)){ this.apellido = apellido;}
        if (!this.teléfono.equals(teléfono)){ this.teléfono = teléfono;}
        if (!this.nota.equals(nota)){ this.nota = nota;}
    }
    public String getNombre() {
        return nombre;
    }

    public String getNota() {
        return nota;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public String getApellido() {

        return apellido;
    }
}
