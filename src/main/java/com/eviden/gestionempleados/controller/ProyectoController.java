package com.eviden.gestionempleados.controller;

import com.eviden.gestionempleados.model.Proyecto;
import com.eviden.gestionempleados.request.ProyectoRequest;
import com.eviden.gestionempleados.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Proyecto>> findAll() {
        try {
            List<Proyecto> empleados = proyectoService.findAll();
            if (empleados.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron proyectos");
            }
            return ResponseEntity.ok(empleados);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener los proyectos", e);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Proyecto> create(@RequestBody ProyectoRequest proyectoRequest) {
        try {
            Proyecto proyecto = proyectoService.create(proyectoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(proyecto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al crear el proyecto", e);
        }
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Proyecto> findById(@PathVariable Long id) {
        try {
            Proyecto proyecto = proyectoService.findById(id);
            if (proyecto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado");
            }
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener el proyecto", e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Proyecto> update(@PathVariable Long id, @RequestBody ProyectoRequest proyectoRequest) {
        try {
            Proyecto proyecto = proyectoService.update(id, proyectoRequest);
            if (proyecto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado");
            }
            return ResponseEntity.ok(proyecto);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar el proyecto", e);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            Proyecto proyecto = proyectoService.findById(id);
            if (proyecto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado");
            }
            proyectoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar el proyecto", e);
        }
    }
}
