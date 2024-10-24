package com.example.empresaTransporte.service;

import com.example.empresaTransporte.model.EntregaModel;
import java.util.List;
import java.util.Optional;

public interface EntregaService {
    EntregaModel crearEntrega(EntregaModel entrega);
    List<EntregaModel> obtenerTodasLasEntregas();
    Optional<EntregaModel> obtenerEntregaPorId(Integer id);
    EntregaModel actualizarEntrega(Integer id, EntregaModel entrega);
    void eliminarEntrega(Integer id);
}