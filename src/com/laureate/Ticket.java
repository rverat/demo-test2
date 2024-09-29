package com.laureate;

import java.time.LocalDateTime;

public class Ticket {

    private String codTicket;
    private Equipo equipo;
    private String codUser;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaResolucion;

    public Ticket(String codTicket, Equipo equipo, String codUser, String descripcion, String estado, LocalDateTime fechaRegistro) {
        this.codTicket = codTicket;
        this.equipo = equipo;
        this.codUser = codUser;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public void actualizarTicket(String descripcion, String estado, LocalDateTime fechaResolucion) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaResolucion = fechaResolucion;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "codTicket='" + codTicket + '\'' +
                ", equipo=" + equipo +
                ", codUser='" + codUser + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                ", fechaResolucion='" + fechaResolucion + '\'' +
                '}';
    }
}
