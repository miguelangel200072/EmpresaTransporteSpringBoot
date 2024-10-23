package com.example.empresaTransporte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PaqueteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaquete;  // ID autoincrement tipo Integer
    
    private String codigoPaquete;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_camionero")
    private CamioneroModel camionero;

    @ManyToOne
    @JoinColumn(name = "idDireccion")
    private DireccionModel idDireccion;  // Nueva relación con DireccionModel

	public Integer getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(Integer idPaquete) {
		this.idPaquete = idPaquete;
	}

	public String getCodigoPaquete() {
		return codigoPaquete;
	}

	public void setCodigoPaquete(String codigoPaquete) {
		this.codigoPaquete = codigoPaquete;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CamioneroModel getCamionero() {
		return camionero;
	}

	public void setCamionero(CamioneroModel camionero) {
		this.camionero = camionero;
	}

	public DireccionModel getidDireccion() {
		return idDireccion;
	}

	public void setidDireccion(DireccionModel idDireccion) {
		this.idDireccion = idDireccion;
	}


}
