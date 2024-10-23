package com.example.empresaTransporte.service;

import java.util.List;
import java.util.Optional;

import com.example.empresaTransporte.model.CamioneroModel;

public interface CamioneroService {
    CamioneroModel crearCamionero(CamioneroModel camionero);
    List<CamioneroModel> obtenerTodosLosCamioneros();
    Optional<CamioneroModel> obtenerCamioneroPorId(Integer id);
    CamioneroModel actualizarCamionero(Integer id, CamioneroModel camionero);
    void eliminarCamionero(Integer id);
}
