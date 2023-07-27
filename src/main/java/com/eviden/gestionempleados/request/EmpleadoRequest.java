package com.eviden.gestionempleados.request;

import java.util.List;

public class EmpleadoRequest {

    private String nombre;
    private String apellidos;
    private List<Long> proyectosIds;

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

    public List<Long> getProyectosIds() {
        return proyectosIds;
    }

    public void setProyectosIds(List<Long> proyectosIds) {
        this.proyectosIds = proyectosIds;
    }
}
