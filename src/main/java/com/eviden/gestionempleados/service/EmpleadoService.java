package com.eviden.gestionempleados.service;

import com.eviden.gestionempleados.model.Empleado;
import com.eviden.gestionempleados.model.Proyecto;
import com.eviden.gestionempleados.reposiroty.EmpleadoRepository;
import com.eviden.gestionempleados.reposiroty.EvaluacionRepository;
import com.eviden.gestionempleados.reposiroty.ProyectoRepository;
import com.eviden.gestionempleados.request.EmpleadoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpleadoService{

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Empleado> findAll() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    public Empleado save(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    public Empleado findById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public void delete(Long empleadoId) {
        empleadoRepository.deleteById(empleadoId);
    }

    public Empleado update(Long id, EmpleadoRequest empleadoRequest) {

        Empleado empleadoExistente = findById(id);

        empleadoExistente.setNombre(empleadoRequest.getNombre());
        empleadoExistente.setApellidos(empleadoRequest.getApellidos());

        List<Proyecto> proyectos = (List<Proyecto>) proyectoRepository.findAllById(empleadoRequest.getProyectosIds());
        if(!proyectos.isEmpty()) {
            empleadoExistente.setProyectos(proyectos);
        }
        return empleadoRepository.save(empleadoExistente);
    }
    public Empleado create(EmpleadoRequest empleadoRequest) {

        String nombre = empleadoRequest.getNombre();
        String apellidos = empleadoRequest.getApellidos();
        List<Proyecto> proyectos = (List<Proyecto>) proyectoRepository.findAllById(empleadoRequest.getProyectosIds());

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellidos(apellidos);
        empleado.setProyectos(proyectos);

        return empleadoRepository.save(empleado);
    }
}
