package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Alumno;

public interface AlumnoService {
    public List<Alumno> listaAlumno();

    // ACA IRAN LOS METODOS
    public Alumno insertaActualizaAlumno(Alumno obj);

    public abstract Optional<Alumno> obtenerPorId(Integer id);

    public void eliminarAlumno(Integer id);

    public abstract List<Alumno> buscaPorDni(String dni);
}
