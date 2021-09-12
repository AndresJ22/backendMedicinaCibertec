package com.empresa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "medicamento")
@Data
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamento;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String laboratorio;

    public Medicamento() {
    }

    @Override
    public String toString() {
        return "{" + " idMedicamento='" + getIdMedicamento() + "'" + ", nombre='" + getNombre() + "'" + ", precio='"
                + getPrecio() + "'" + ", stock='" + getStock() + "'" + ", laboratorio='" + getLaboratorio() + "'" + "}";
    }

    // lombok metodos get and setter
}
