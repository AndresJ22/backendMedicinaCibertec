package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.empresa.entity.Medicamento;
import com.empresa.service.IMedicamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rest")
public class MedicamentoRestController {
    @Autowired
    private IMedicamentoService medicamentoService;

    @GetMapping("/medicamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<Medicamento> listarMedicamentos() {
        return medicamentoService.findAll();
    }

    @GetMapping("/medicamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        // RESPONSE ENTITY PARA EL MANEJO DE ERRORES AL MOMENTO DE MANDAR POR JSON
        Medicamento medicamento = null;
        Map<String, Object> response = new HashMap<>();
        try {
            medicamento = medicamentoService.findById(id);
        } catch (DataAccessException e) { // para obtener los errores en base datos
            response.put("mensaje", "Error a realizar la consulta en la base datos ");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (medicamento == null) {
            // SI NO HAY MEDICAMENTO EN LA RUTA GET RETORNARA UNA RESPUESTA CON UN CODIGO
            // 404
            response.put("mensaje",
                    "El medicamento ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Medicamento>(medicamento, HttpStatus.OK);
    }

    @PostMapping("/medicamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> CrearMedicamento(@RequestBody Medicamento medicamento) {
        Medicamento medicamentoNew = null;
        Map<String, Object> response = new HashMap<>();
        try {
            medicamentoNew = medicamentoService.save(medicamento);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El medicamento ha sido creado con éxito!");
        response.put("medicamento", medicamentoNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/medicamentos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> ActualizarMedicamento(@RequestBody Medicamento medicamento, @PathVariable Long id) {
        Medicamento medicamentoActual = medicamentoService.findById(id);
        Medicamento medicamentoUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if (medicamentoActual == null) {
            response.put("mensaje", "error: nose puede editar, El medicamento ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            medicamentoActual.setNombre(medicamento.getNombre());
            medicamentoActual.setPrecio(medicamento.getPrecio());
            medicamentoActual.setStock(medicamento.getStock());
            medicamentoActual.setLaboratorio(medicamento.getLaboratorio());
            medicamentoUpdated = medicamentoService.save(medicamentoActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el medicamento en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El medicamento ha sido actualizado con éxito!");
        response.put("medicamento", medicamentoUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/medicamentos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> eliminarMedicamento(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            medicamentoService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el medicamento en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "medicamento eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}