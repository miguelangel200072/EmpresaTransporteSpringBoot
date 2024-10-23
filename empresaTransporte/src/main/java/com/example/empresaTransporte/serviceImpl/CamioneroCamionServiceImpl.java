package com.example.empresaTransporte.serviceImpl;

import com.example.empresaTransporte.model.CamionModel;
import com.example.empresaTransporte.model.CamioneroModel;
import com.example.empresaTransporte.model.Camionero_Camion;
import com.example.empresaTransporte.repository.CamionRepository;
import com.example.empresaTransporte.repository.CamioneroCamionRepository;
import com.example.empresaTransporte.repository.CamioneroRepository;
import com.example.empresaTransporte.service.CamioneroCamionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate; // Asegúrate de importar LocalDate
@Service
public class CamioneroCamionServiceImpl implements CamioneroCamionService {

    @Autowired
    private CamioneroCamionRepository camioneroCamionRepository;
    
    @Autowired
    private CamioneroRepository camioneroRepository;
    
    @Autowired
    private CamionRepository camionRepository;

    /*@Override
    public Camionero_Camion crearCamioneroCamion(Camionero_Camion camioneroCamion) {
        try {
            return camioneroCamionRepository.save(camioneroCamion);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al crear la relación Camionero-Camión: " + e.getMessage());
        }
    }*/
    


    @Override
    public Camionero_Camion crearCamioneroCamion(Camionero_Camion camioneroCamion) {
        try {
            // Cargar completamente el camionero
            Optional<CamioneroModel> camioneroOptional = camioneroRepository.findById(camioneroCamion.getCamionero().getIdCamionero());
            if (camioneroOptional.isPresent()) {
                camioneroCamion.setCamionero(camioneroOptional.get());
            } else {
                throw new RuntimeException("Camionero no encontrado con ID: " + camioneroCamion.getCamionero().getIdCamionero());
            }

            // Cargar completamente el camión
            Optional<CamionModel> camionOptional = camionRepository.findById(camioneroCamion.getCamion().getIdCamion());
            if (camionOptional.isPresent()) {
                camioneroCamion.setCamion(camionOptional.get());
            } else {
                throw new RuntimeException("Camión no encontrado con ID: " + camioneroCamion.getCamion().getIdCamion());
            }

            // Si la fecha es null, establecer la fecha actual
            if (camioneroCamion.getFecha() == null) {
                camioneroCamion.setFecha(LocalDate.now());
            }

            return camioneroCamionRepository.save(camioneroCamion);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al crear la relación Camionero-Camión: " + e.getMessage());
        }
    }


    @Override
    public List<Camionero_Camion> obtenerTodosLosCamionerosCamiones() {
        try {
            return camioneroCamionRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al obtener las relaciones Camionero-Camión: " + e.getMessage());
        }
    }

    @Override
    public Optional<Camionero_Camion> obtenerCamioneroCamionPorId(Integer id) {
        try {
            return camioneroCamionRepository.findById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al obtener la relación Camionero-Camión por ID: " + e.getMessage());
        }
    }

    @Override
    public Camionero_Camion actualizarCamioneroCamion(Integer id, Camionero_Camion camioneroCamion) {
        try {
            if (camioneroCamionRepository.existsById(id)) {
                // Comprobar si el camionero existe
                Optional<CamioneroModel> camioneroOptional = camioneroRepository.findById(camioneroCamion.getCamionero().getIdCamionero());
                if (!camioneroOptional.isPresent()) {
                    throw new RuntimeException("Camionero no encontrado con ID: " + camioneroCamion.getCamionero().getIdCamionero());
                }
                camioneroCamion.setCamionero(camioneroOptional.get());

                // Comprobar si el camión existe
                Optional<CamionModel> camionOptional = camionRepository.findById(camioneroCamion.getCamion().getIdCamion());
                if (!camionOptional.isPresent()) {
                    throw new RuntimeException("Camión no encontrado con ID: " + camioneroCamion.getCamion().getIdCamion());
                }
                camioneroCamion.setCamion(camionOptional.get());

                camioneroCamion.setIdConduccion(id);

                // Si la fecha es null, establecer la fecha actual
                if (camioneroCamion.getFecha() == null) {
                    camioneroCamion.setFecha(LocalDate.now());
                }
                return camioneroCamionRepository.save(camioneroCamion);
            } else {
                throw new RuntimeException("Relación no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar la relación Camionero-Camión: " + e.getMessage());
        }
    }


    @Override
    public void eliminarCamioneroCamion(Integer id) {
        try {
            if (camioneroCamionRepository.existsById(id)) {
                camioneroCamionRepository.deleteById(id);
            } else {
                throw new RuntimeException("Relación no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al eliminar la relación Camionero-Camión: " + e.getMessage());
        }
    }
}
