package com.laureate;

import com.laureate.exception.TicketNoEncontradoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PersonalOnsite extends Persona {

    protected String email;
    protected String password;

    private List<Ticket> tickets = new ArrayList<>();

    public PersonalOnsite(String codPersona, String nombre, String email, String password) {
        super(codPersona, nombre);
        this.email = email;
        this.password = password;

    }
    public void enviarCorreo(String mensaje) {
        System.out.println("Enviando correo: " + mensaje);
    }

    @Override
    public void mostrarMenu() {
        // Lógica de menú para el personal onsite
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