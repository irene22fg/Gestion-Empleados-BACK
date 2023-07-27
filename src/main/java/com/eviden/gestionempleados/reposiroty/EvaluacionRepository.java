package com.eviden.gestionempleados.reposiroty;

import com.eviden.gestionempleados.model.Evaluacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends CrudRepository<Evaluacion, Long> {
}
