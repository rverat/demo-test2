package com.laureate;

public class Equipo {

    private String codEquipo;
    private String tipoEquipo;
    private String descripcion;

    public Equipo(String codEquipo, String tipoEquipo, String descripcion) {
        this.codEquipo = codEquipo;
        this.tipoEquipo = tipoEquipo;
        this.descripcion = descripcion;
    }

    public String getCodEquipo() {
        return codEquipo;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "codEquipo='" + codEquipo + '\'' +
                ", tipoEquipo='" + tipoEquipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
