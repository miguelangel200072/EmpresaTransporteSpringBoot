package com.example.empresaTransporte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class CamionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCamion;  // ID autoincrement tipo Integer
    
    private String matricula;
    private String modelo;
    private double kilometros;
    
    @ManyToMany(mappedBy = "camiones")
    @JsonIgnore
    private List<CamioneroModel> camioneros;

	public Integer getIdCamion() {
		return idCamion;
	}

	public void setIdCamion(Integer idCamion) {
		this.idCamion = idCamion;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public double getKilometros() {
		return kilometros;
	}

	public void setKilometros(double kilometros) {
		this.kilometros = kilometros;
	}

	public List<CamioneroModel> getCamioneros() {
		return camioneros;
	}

	public void setCamioneros(List<CamioneroModel> camioneros) {
		this.camioneros = camioneros;
	}

}
