package com.laureate;

import com.laureate.exception.EquipoDuplicadoException;

import java.util.List;

public interface GestionEquipos {
    void registrarEquipo(Equipo equipo) throws EquipoDuplicadoException;
    List<Equipo> listarEquipos();
}