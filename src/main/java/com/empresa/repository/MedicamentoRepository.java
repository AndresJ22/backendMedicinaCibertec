package com.empresa.repository;

import com.empresa.entity.Medicamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
