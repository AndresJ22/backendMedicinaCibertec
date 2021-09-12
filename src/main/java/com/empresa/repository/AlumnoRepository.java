package com.empresa.repository;

import java.util.List;

import com.empresa.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    // @Query("select x from Alumno x where x.dni = :param_dni")
    // public List<Alumno> buscaPorDni(@Param("param_dni") String dni);
    public List<Alumno> findByDni(String dni);
}
