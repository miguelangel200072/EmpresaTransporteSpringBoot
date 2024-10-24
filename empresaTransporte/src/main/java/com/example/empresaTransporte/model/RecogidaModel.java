package com.example.empresaTransporte.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recogidas")
public class RecogidaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRecogida;

    @ManyToOne
    @JoinColumn(name = "idPaquete", nullable = false)
    private PaqueteModel paquete;

    private LocalDate fechaRecogida;

    private String estado;

    // Getters y Setters
    public Integer getIdRecogida() {
        return idRecogida;
    }

    public void setIdRecogida(Integer idRecogida) {
        this.idRecogida = idRecogida;
    }

    public PaqueteModel getPaquete() {
        return paquete;
    }

    public void setPaquete(PaqueteModel paquete) {
        this.paquete = paquete;
    }

    public LocalDate getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(LocalDate fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
