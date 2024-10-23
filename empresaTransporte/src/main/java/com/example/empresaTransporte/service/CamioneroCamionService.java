package com.example.empresaTransporte.service;

import com.example.empresaTransporte.model.Camionero_Camion;
import java.util.List;
import java.util.Optional;

public interface CamioneroCamionService {
    Camionero_Camion crearCamioneroCamion(Camionero_Camion camioneroCamion);
    List<Camionero_Camion> obtenerTodosLosCamionerosCamiones();
    Optional<Camionero_Camion> obtenerCamioneroCamionPorId(Integer id);
    Camionero_Camion actualizarCamioneroCamion(Integer id, Camionero_Camion camioneroCamion);
    void eliminarCamioneroCamion(Integer id);
}
