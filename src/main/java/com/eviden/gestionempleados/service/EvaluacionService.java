package com.eviden.gestionempleados.service;

import com.eviden.gestionempleados.model.Empleado;
import com.eviden.gestionempleados.model.Evaluacion;
import com.eviden.gestionempleados.reposiroty.EmpleadoRepository;
import com.eviden.gestionempleados.reposiroty.EvaluacionRepository;
import com.eviden.gestionempleados.request.EvaluacionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Evaluacion> findAll() { return (List<Evaluacion>) evaluacionRepository.findAll(); }

    public Evaluacion save(Evaluacion evaluacion) { return evaluacionRepository.save(evaluacion); }

    public Evaluacion findById(Long id) {
        return evaluacionRepository.findById(id).orElse(null);
    }

    public void delete(Long evaluacionId) {
        evaluacionRepository.deleteById(evaluacionId);
    }

    public Evaluacion update(Long id, EvaluacionRequest evaluacionRequest) throws ChangeSetPersister.NotFoundException {

        Evaluacion evaluacion = findById(id);

        evaluacion.setNota(evaluacionRequest.getNota());
        evaluacion.setEmpleado(empleadoRepository.findById(evaluacionRequest.getEmpleadoId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()));

        Date createAt = evaluacionRequest.getCreateAt();
        if(createAt != null)
        evaluacion.setCreateAt(evaluacionRequest.getCreateAt());

        return evaluacionRepository.save(evaluacion);
    }
    public Evaluacion create(EvaluacionRequest evaluacionRequest) throws ChangeSetPersister.NotFoundException{

        int nota = evaluacionRequest.getNota();
        Empleado empleado = empleadoRepository.findById(evaluacionRequest.getEmpleadoId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNota(nota);
        evaluacion.setEmpleado(empleado);

        return evaluacionRepository.save(evaluacion);
    }
}
