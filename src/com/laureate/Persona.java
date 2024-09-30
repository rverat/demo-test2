package com.laureate;

abstract class Persona {
    protected String codPersona;
    protected String nombre;

    public Persona(String codPersona, String nombre) {
        this.codPersona = codPersona;
        this.nombre = nombre;
    }

    public abstract void mostrarMenu();

    public String getCodPersona() {
        return codPersona;
    }

    public void setCodPersona(String codPersona) {
        this.codPersona = codPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}