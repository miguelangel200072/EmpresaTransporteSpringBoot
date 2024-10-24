package com.example.empresaTransporte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DireccionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDireccion;
    
    private String tipoVia;
    private String nombreVia;
    private String numero;
    private String codigoPostal;
    
    @ManyToOne
    @JoinColumn(name = "id_ciudad")
    @JsonIgnoreProperties("direcciones")
    private CiudadModel ciudad;

    @OneToMany(mappedBy = "idDireccion")
    @JsonIgnore
    private List<PaqueteModel> paquetes;

	public Integer getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public CiudadModel getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadModel ciudad) {
		this.ciudad = ciudad;
	}

	public List<PaqueteModel> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaqueteModel> paquetes) {
		this.paquetes = paquetes;
	}


}
