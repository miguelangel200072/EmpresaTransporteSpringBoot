package com.example.empresaTransporte.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empresaTransporte.model.CiudadModel;
import com.example.empresaTransporte.repository.CiudadRepository;
import com.example.empresaTransporte.service.CiudadService;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServiceImpl implements CiudadService {

    @Autowired
    private CiudadRepository ciudadRepository;

    @Override
    public CiudadModel crearCiudad(CiudadModel ciudad) {
        try {
            return ciudadRepository.save(ciudad);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear la ciudad: " + e.getMessage(), e);
        }
    }

    @Override
    public List<CiudadModel> obtenerTodasLasCiudades() {
        try {
            return ciudadRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todas las ciudades: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<CiudadModel> obtenerCiudadPorId(Integer id) {
        try {
            return ciudadRepository.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la ciudad por ID: " + e.getMessage(), e);
        }
    }

    @Override
    public CiudadModel actualizarCiudad(Integer id, CiudadModel ciudad) {
        try {
            // Verificar si la ciudad con el ID especificado existe
            if (ciudadRepository.existsById(id)) {
                ciudad.setIdCiudad(id);
                return ciudadRepository.save(ciudad);
            } else {
                throw new RuntimeException("No se encontró una ciudad con el ID especificado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar la ciudad: " + e.getMessage(), e);
        }
    }


    @Override
    public void eliminarCiudad(Integer id) {
        try {
            ciudadRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar la ciudad: " + e.getMessage(), e);
        }
    }
}
