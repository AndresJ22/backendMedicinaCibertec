package com.empresa.service;

import java.util.List;

import com.empresa.entity.Medicamento;

public interface IMedicamentoService {

    public List<Medicamento> findAll();

    public Medicamento findById(Long id);

    public Medicamento save(Medicamento medicamento);

    public void delete(Long id);

}
