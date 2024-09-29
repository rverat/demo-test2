package com.laureate;

import com.laureate.exception.TicketNoEncontradoException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestionTicketImpl implements GestionTickets{


    private List<Ticket> tickets = new ArrayList<>();
    @Override
    public void registrarTicket(Ticket ticket) {
        tickets.add(ticket);
        System.out.println("Ticket registrado correctamente.");
    }

    @Override
    public void actualizarTicket(String codTicket, String descripcion, String estado) throws TicketNoEncontradoException {
        for (Ticket ticket : tickets) {
            if (ticket.toString().contains(codTicket)) {
                ticket.actualizarTicket(descripcion, estado, LocalDateTime.now());
                System.out.println("Ticket actualizado correctamente.");
                return;
            }
        }
        throw new TicketNoEncontradoException("Ticket no encontrado.");
    }

    @Override
    public List<Ticket> listarTickets() {
        return tickets;
    }
}
