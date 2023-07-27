package com.eviden.gestionempleados.reposiroty;

import com.eviden.gestionempleados.model.Proyecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends CrudRepository<Proyecto, Long> {
}
