package com.eviden.gestionempleados.service;

import com.eviden.gestionempleados.model.Proyecto;
import com.eviden.gestionempleados.reposiroty.EmpleadoRepository;
import com.eviden.gestionempleados.reposiroty.ProyectoRepository;
import com.eviden.gestionempleados.request.ProyectoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Proyecto> findAll() {
        return (List<Proyecto>) proyectoRepository.findAll();
    }

    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto findById(Long id) {
        return proyectoRepository.findById(id).orElse(null);
    }

    public void delete(Long proyectoId) {
        proyectoRepository.deleteById(proyectoId);
    }

    public Proyecto update(Long id, ProyectoRequest proyectoRequest) throws ChangeSetPersister.NotFoundException {

        Proyecto proyecto = findById(id);

        proyecto.setNombre(proyectoRequest.getNombre());
        proyecto.setDescripcion(proyectoRequest.getDescripcion());

        return proyectoRepository.save(proyecto);
    }
    public Proyecto create(ProyectoRequest proyectoRequest) throws ChangeSetPersister.NotFoundException{

        String nombre = proyectoRequest.getNombre();
        String descripcion = proyectoRequest.getDescripcion();

        Proyecto proyecto = new Proyecto();
        proyecto.setNombre(nombre);
        proyecto.setDescripcion(descripcion);

        return proyectoRepository.save(proyecto);
    }
}
