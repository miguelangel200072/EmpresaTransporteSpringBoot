package com.example.empresaTransporte.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empresaTransporte.model.CamionModel;
import com.example.empresaTransporte.repository.CamionRepository;
import com.example.empresaTransporte.service.CamionService;

import java.util.List;
import java.util.Optional;

@Service
public class CamionServiceImpl implements CamionService {

    @Autowired
    private CamionRepository camionRepository;

    @Override
    public CamionModel crearCamion(CamionModel camion) {
        try {
            return camionRepository.save(camion);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el camión: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CamionModel> obtenerTodosLosCamiones() {
        try {
            return camionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los camiones: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<CamionModel> obtenerCamionPorId(Integer id) {
        try {
            return camionRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el camión por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public CamionModel actualizarCamion(Integer id, CamionModel camion) {
        try {
            // Verificar si el camión con el ID especificado existe
            if (camionRepository.existsById(id)) {
                camion.setIdCamion(id);
                return camionRepository.save(camion);
            } else {
                throw new RuntimeException("No se encontró un camión con el ID especificado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el camión: " + e.getMessage(), e);
        }
    }


    @Override
    public void eliminarCamion(Integer id) {
        try {
            camionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el camión: " + e.getMessage(), e);
        }
    }
}
