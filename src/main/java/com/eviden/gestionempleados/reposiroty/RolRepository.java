package com.eviden.gestionempleados.reposiroty;

import com.eviden.gestionempleados.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
}
