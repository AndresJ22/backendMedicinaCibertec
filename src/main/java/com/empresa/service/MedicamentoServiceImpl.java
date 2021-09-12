package com.empresa.service;

import java.util.List;

import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Medicamento> findAll() {
        List<Medicamento> medicamentos = medicamentoRepository.findAll();
        // TODO Auto-generated method stub
        return medicamentos;
    }

    @Override
    public Medicamento findById(Long id) {
        // TODO Auto-generated method stub
        return medicamentoRepository.findById(id).orElse(null);
    }

    @Override
    public Medicamento save(Medicamento medicamento) {
        // TODO Auto-generated method stub
        return medicamentoRepository.save(medicamento);
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        medicamentoRepository.deleteById(id);
    }

}
