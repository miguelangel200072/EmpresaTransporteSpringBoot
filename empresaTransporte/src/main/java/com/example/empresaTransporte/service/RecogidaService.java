package com.example.empresaTransporte.service;

import com.example.empresaTransporte.model.RecogidaModel;
import java.util.List;
import java.util.Optional;

public interface RecogidaService {
    RecogidaModel crearRecogida(RecogidaModel recogida);
    List<RecogidaModel> obtenerTodasLasRecogidas();
    Optional<RecogidaModel> obtenerRecogidaPorId(Integer id);
    RecogidaModel actualizarRecogida(Integer id, RecogidaModel recogida);
    void eliminarRecogida(Integer id);
}