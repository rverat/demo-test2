package com.laureate;

import com.laureate.exception.TicketNoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class PersonalAulaYLab extends Persona {

    protected String email;
    protected String password;

    public PersonalAulaYLab(String codPersona, String nombre, String email, String password) {
        super(codPersona, nombre);
        this.email = email;
        this.password = password;

    }

    @Override
    public void mostrarMenu() {
        // Lógica de menú para el personal Aula y Laboratorios
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}