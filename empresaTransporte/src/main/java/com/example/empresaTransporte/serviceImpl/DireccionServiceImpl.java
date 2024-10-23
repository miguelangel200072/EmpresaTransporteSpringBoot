package com.example.empresaTransporte.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empresaTransporte.model.DireccionModel;
import com.example.empresaTransporte.repository.DireccionRepository;
import com.example.empresaTransporte.service.DireccionService;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImpl implements DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public DireccionModel crearDireccion(DireccionModel direccion) {
        try {
            return direccionRepository.save(direccion);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la dirección: " + e.getMessage(), e);
        }
    }

    @Override
    public List<DireccionModel> obtenerTodasLasDirecciones() {
        try {
            return direccionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las direcciones: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<DireccionModel> obtenerDireccionPorId(Integer id) {
        try {
            return direccionRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la dirección por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public DireccionModel actualizarDireccion(Integer id, DireccionModel direccion) {
        try {
            direccion.setIdDireccion(id); // Asegúrate de tener este método en tu modelo
            return direccionRepository.save(direccion);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la dirección: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarDireccion(Integer id) {
        try {
            direccionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la dirección: " + e.getMessage(), e);
        }
    }
}
