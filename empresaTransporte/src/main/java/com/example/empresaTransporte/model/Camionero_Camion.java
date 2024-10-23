package com.example.empresaTransporte.model;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Camionero_Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConduccion;  // ID autoincrement tipo Integer
    
    private LocalDate fecha;
    
    @ManyToOne
    @JoinColumn(name = "id_camionero")
    private CamioneroModel camionero;
    
    @ManyToOne
    @JoinColumn(name = "id_camion")
    private CamionModel camion;

	public Integer getIdConduccion() {
		return idConduccion;
	}

	public void setIdConduccion(Integer idConduccion) {
		this.idConduccion = idConduccion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate localDate) {
		this.fecha = localDate;
	}

	public CamioneroModel getCamionero() {
		return camionero;
	}

	public void setCamionero(CamioneroModel camionero) {
		this.camionero = camionero;
	}

	public CamionModel getCamion() {
		return camion;
	}

	public void setCamion(CamionModel camion) {
		this.camion = camion;
	}

}
