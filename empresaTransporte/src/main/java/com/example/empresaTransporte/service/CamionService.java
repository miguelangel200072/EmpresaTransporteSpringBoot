package com.example.empresaTransporte.service;

import java.util.List;
import java.util.Optional;

import com.example.empresaTransporte.model.CamionModel;

public interface CamionService {
    CamionModel crearCamion(CamionModel camion);
    List<CamionModel> obtenerTodosLosCamiones();
    Optional<CamionModel> obtenerCamionPorId(Integer id);
    CamionModel actualizarCamion(Integer id, CamionModel camion);
    void eliminarCamion(Integer id);
}
