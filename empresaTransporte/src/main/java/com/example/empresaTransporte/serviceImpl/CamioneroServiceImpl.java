package com.example.empresaTransporte.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empresaTransporte.model.CamioneroModel;
import com.example.empresaTransporte.repository.CamioneroRepository;
import com.example.empresaTransporte.service.CamioneroService;

@Service
public class CamioneroServiceImpl implements CamioneroService {

    @Autowired
    private CamioneroRepository camioneroRepository;

    @Override
    public CamioneroModel crearCamionero(CamioneroModel camionero) {
        try {
            return camioneroRepository.save(camionero);
        } catch (Exception e) {
            // Manejar el error y lanzar una excepción personalizada o un mensaje
            throw new RuntimeException("Error al crear el camionero: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CamioneroModel> obtenerTodosLosCamioneros() {
        try {
            return camioneroRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los camioneros: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<CamioneroModel> obtenerCamioneroPorId(Integer id) {
        try {
            return camioneroRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el camionero por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public CamioneroModel actualizarCamionero(Integer id, CamioneroModel camionero) {
        try {
            camionero.setIdCamionero(id);
            return camioneroRepository.save(camionero);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el camionero: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminarCamionero(Integer id) {
        try {
            camioneroRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el camionero: " + e.getMessage(), e);
        }
    }
}

