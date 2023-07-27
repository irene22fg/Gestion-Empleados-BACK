package com.eviden.gestionempleados.controller;

import com.eviden.gestionempleados.model.Evaluacion;
import com.eviden.gestionempleados.request.EvaluacionRequest;
import com.eviden.gestionempleados.service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @GetMapping
    public ResponseEntity<List<Evaluacion>> findAll() {
        try {
            List<Evaluacion> empleados = evaluacionService.findAll();
            if (empleados.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron evaluaciones");
            }
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener las evaluaciones", e);
        }
    }

    @PostMapping
    public ResponseEntity<Evaluacion> create(@RequestBody EvaluacionRequest evaluacionRequest) {
        try {
            Evaluacion evaluacion = evaluacionService.create(evaluacionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(evaluacion);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear la evaluacion", e);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> findById(@PathVariable Long id) {
        try {
            Evaluacion evaluacion = evaluacionService.findById(id);
            if (evaluacion == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluacion no encontrado");
            }
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener la evaluacion", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluacion> update(@PathVariable Long id, @RequestBody EvaluacionRequest evaluacionRequest) {
        try {
            Evaluacion evaluacion = evaluacionService.update(id, evaluacionRequest);
            if (evaluacion == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluacion no encontrado");
            }
            return ResponseEntity.ok(evaluacion);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la evaluacion", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Evaluacion evaluacion = evaluacionService.findById(id);
            if (evaluacion == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Evaluacion no encontrado");
            }
            evaluacionService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar la evaluacion", e);
        }
    }
}
