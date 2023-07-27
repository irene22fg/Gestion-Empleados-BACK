package com.eviden.gestionempleados.service;

import com.eviden.gestionempleados.model.Rol;
import com.eviden.gestionempleados.reposiroty.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return (List<Rol>) rolRepository.findAll();
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public Rol findById(Long id) {
        return rolRepository.findById(id).orElse(null);
    }

    public void delete(Long rolId) {
        rolRepository.deleteById(rolId);
    }
}
