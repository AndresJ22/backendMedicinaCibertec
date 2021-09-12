package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Alumno;
import com.empresa.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServiceImpl implements AlumnoService {
    @Autowired
    private AlumnoRepository alummnoRepository;

    @Override
    public List<Alumno> listaAlumno() {
        // TODO Auto-generated method stub
        return alummnoRepository.findAll();
    }

    @Override
    public Alumno insertaActualizaAlumno(Alumno obj) {
        // TODO Auto-generated method stub
        return alummnoRepository.save(obj);
    }

    @Override
    public Optional<Alumno> obtenerPorId(Integer idAlumno) {

        return alummnoRepository.findById(idAlumno);
    }

    @Override
    public void eliminarAlumno(Integer id) {
        // TODO Auto-generated method stub
        alummnoRepository.deleteById(id);

    }

    @Override

    public List<Alumno> buscaPorDni(String dni) {
        return alummnoRepository.findByDni(dni);
    }

}
