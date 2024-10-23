package com.example.empresaTransporte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class CiudadModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCiudad;  // ID autoincrement tipo Integer
    
	private String nombreCiudad;
    
    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private List<DireccionModel> direcciones;  // Relación One-to-Many con DireccionModel
    
    public Integer getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public List<DireccionModel> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<DireccionModel> direcciones) {
		this.direcciones = direcciones;
	}



}
