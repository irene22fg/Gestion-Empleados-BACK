package com.eviden.gestionempleados.service;

import com.eviden.gestionempleados.model.*;
import com.eviden.gestionempleados.reposiroty.EmpleadoRepository;
import com.eviden.gestionempleados.reposiroty.RolRepository;
import com.eviden.gestionempleados.reposiroty.UsuarioRepository;
import com.eviden.gestionempleados.request.UsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleResult;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void delete(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario update(Long id, UsuarioRequest usuarioRequest) throws ChangeSetPersister.NotFoundException {

        Usuario usuarioExistente = findById(id);

        usuarioExistente.setEmail(usuarioRequest.getEmail());
        usuarioExistente.setNombre(usuarioRequest.getNombre());
        usuarioExistente.setContrasena(usuarioRequest.getContrasena()); //Hay que encriptarla
        usuarioExistente.setEmpleado(empleadoRepository.findById(usuarioRequest.getEmpleadoId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException()));

        Set<Rol> roles = (Set<Rol>) rolRepository.findAllById(usuarioRequest.getRolesIds());
        if(!roles.isEmpty()) {
            usuarioExistente.setRoles(roles);
        }

        return usuarioRepository.save(usuarioExistente);
    }
    public Usuario create(UsuarioRequest usuarioRequest) throws ChangeSetPersister.NotFoundException {

        String email = usuarioRequest.getEmail();
        String nombre = usuarioRequest.getNombre();
        String contrasena = passwordEncoder.encode(usuarioRequest.getContrasena());
        Empleado empleado = empleadoRepository.findById(usuarioRequest.getEmpleadoId())
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        HashSet<Rol> roles = new HashSet<>((Collection) rolRepository.findAllById(usuarioRequest.getRolesIds()));

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setContrasena(contrasena);
        usuario.setEmpleado(empleado);
        usuario.setRoles(roles);

        return usuarioRepository.save(usuario);
    }
}
