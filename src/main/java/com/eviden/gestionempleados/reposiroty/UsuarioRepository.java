package com.eviden.gestionempleados.reposiroty;

import com.eviden.gestionempleados.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);
    Usuario findByEmpleadoId(Long id);

}
