package com.example.empresaTransporte.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class CamioneroModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCamionero;
	
	private String dni;
	
	private String nombre;
	
	private String telefono;
	
	private String direccion;
	
	private double salario;
	
	@OneToMany(mappedBy = "camionero")
	@JsonIgnore
	List<PaqueteModel> paquetes;
	
	@ManyToMany
	@JoinTable(
			name = "camionero_camion",
			joinColumns = @JoinColumn(name = "id_camionero"),
			inverseJoinColumns = @JoinColumn(name = "id_camion")
			)
    @JsonIgnore
	private List<CamionModel> camiones;
	@OneToOne
    @JoinColumn(name = "idUsuario")
	@JsonIgnore
    private UsuarioModel usuario;
	
	public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

	public Integer getIdCamionero() {
		return idCamionero;
	}

	public void setIdCamionero(Integer idCamionero) {
		this.idCamionero = idCamionero;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public List<PaqueteModel> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaqueteModel> paquetes) {
		this.paquetes = paquetes;
	}

	public List<CamionModel> getCamiones() {
		return camiones;
	}

	public void setCamiones(List<CamionModel> camiones) {
		this.camiones = camiones;
	}
}
