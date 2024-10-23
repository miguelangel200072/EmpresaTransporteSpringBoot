package com.example.empresaTransporte.service;

import java.util.List;
import java.util.Optional;

import com.example.empresaTransporte.model.DireccionModel;

public interface DireccionService {
    DireccionModel crearDireccion(DireccionModel direccion);
    List<DireccionModel> obtenerTodasLasDirecciones();
    Optional<DireccionModel> obtenerDireccionPorId(Integer id);
    DireccionModel actualizarDireccion(Integer id, DireccionModel direccion);
    void eliminarDireccion(Integer id);
}
