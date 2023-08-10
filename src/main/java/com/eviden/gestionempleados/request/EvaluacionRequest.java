package com.eviden.gestionempleados.request;


public class EvaluacionRequest {

    private int nota;
    private Long empleadoId;

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Long getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Long empleadoId) {
        this.empleadoId = empleadoId;
    }

}
