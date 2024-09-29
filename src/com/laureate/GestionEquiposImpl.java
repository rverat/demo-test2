package com.laureate;

import com.laureate.exception.EquipoDuplicadoException;

import java.util.ArrayList;
import java.util.List;

public class GestionEquiposImpl implements GestionEquipos{

    private List<Equipo> equipos = new ArrayList<>();

    @Override
    public void registrarEquipo(Equipo equipo) throws EquipoDuplicadoException {
        for (Equipo e : equipos) {
            if (e.getCodEquipo().equals(equipo.getCodEquipo())) {
                throw new EquipoDuplicadoException("El código del equipo ya existe.");
            }
        }
        equipos.add(equipo);
        System.out.println("Equipo registrado correctamente.");
    }

    @Override
    public List<Equipo> listarEquipos() {
        return equipos;
    }
}
