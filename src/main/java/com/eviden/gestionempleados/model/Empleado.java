package com.eviden.gestionempleados.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    @NotEmpty
    @Size(min=4)
    private String nombre;
    @Column(nullable=false)
    @NotEmpty
    private String apellidos;
    @OneToOne(mappedBy = "empleado", cascade = CascadeType.ALL)
    @JsonIgnore
    private Usuario usuario;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empleado", cascade = CascadeType.ALL)
    private List<Evaluacion> evaluaciones;
    @ManyToMany
    @JoinTable(name = "empleado_proyecto",
            joinColumns = @JoinColumn(name = "empleado_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "proyecto_id", referencedColumnName = "id"))
    private List<Proyecto> proyectos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
