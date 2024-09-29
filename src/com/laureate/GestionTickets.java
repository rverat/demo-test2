package com.laureate;

import com.laureate.exception.TicketNoEncontradoException;

import java.util.List;

public interface GestionTickets {
    void registrarTicket(Ticket ticket);
    void actualizarTicket(String codTicket, String descripcion, String estado) throws TicketNoEncontradoException;
    List<Ticket> listarTickets();
}

