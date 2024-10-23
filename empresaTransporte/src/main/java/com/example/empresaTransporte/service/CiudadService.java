package com.example.empresaTransporte.service;

import java.util.List;
import java.util.Optional;

import com.example.empresaTransporte.model.CiudadModel;

public interface CiudadService {
    CiudadModel crearCiudad(CiudadModel ciudad);
    List<CiudadModel> obtenerTodasLasCiudades();
    Optional<CiudadModel> obtenerCiudadPorId(Integer id);
    CiudadModel actualizarCiudad(Integer id, CiudadModel ciudad);
    void eliminarCiudad(Integer id);
}
