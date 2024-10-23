package com.example.empresaTransporte.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.empresaTransporte.model.CamioneroModel;
import com.example.empresaTransporte.model.DireccionModel;
import com.example.empresaTransporte.model.PaqueteModel;
import com.example.empresaTransporte.repository.CamioneroRepository;
import com.example.empresaTransporte.repository.DireccionRepository;
import com.example.empresaTransporte.repository.PaqueteRepository;
import com.example.empresaTransporte.service.PaqueteService;

@Service
public class PaqueteServiceImpl implements PaqueteService {
    
    @Autowired
    PaqueteRepository paqueteRepo;

    @Autowired
    CamioneroRepository camioneroRepo;

    @Autowired
    DireccionRepository direccionRepo;

    @Override
    public PaqueteModel guardaPaquete(PaqueteModel paquete) {
        try {
            // Verifica que el camionero existe
            if (paquete.getCamionero() != null) {
                Optional<CamioneroModel> camioneroExistente = camioneroRepo.findById(paquete.getCamionero().getIdCamionero());
                if (camioneroExistente.isPresent()) {
                    paquete.setCamionero(camioneroExistente.get());
                } else {
                    System.out.println("[guardaPaquete] Camionero no encontrado: " + paquete.getCamionero().getIdCamionero());
                    return null;
                }
            }

            // Verifica que la dirección existe
            if (paquete.getidDireccion() != null) {
                Optional<DireccionModel> direccionExistente = direccionRepo.findById(paquete.getidDireccion().getIdDireccion());
                if (direccionExistente.isPresent()) {
                    paquete.setidDireccion(direccionExistente.get());
                } else {
                    System.out.println("[guardaPaquete] Dirección no encontrada: " + paquete.getidDireccion().getIdDireccion());
                    return null;
                }
            }

            return paqueteRepo.save(paquete);
        } catch (Exception e) {
            System.out.println("[guardaPaquete] exception: " + e.getMessage());
            return null;
        }
    }

    @Override
    public PaqueteModel getPaqueteByID(Integer id) {
        PaqueteModel result = null;
        try {
            Optional<PaqueteModel> optionalPaquete = paqueteRepo.findById(id);
            if (optionalPaquete.isPresent()) {
                result = optionalPaquete.get();
            }
        } catch (Exception e) {
            System.out.println("[getPaqueteByID] exception: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<PaqueteModel> getAllPaquetes() {
        List<PaqueteModel> result = new ArrayList<>();
        try {
            result = paqueteRepo.findAll();
        } catch (Exception e) {
            System.out.println("[getAllPaquetes] exception: " + e.getMessage());
        }
        return result;
    }

    @Override
    public void deletePaqueteByID(Integer idPaquete) {
        try {
            Optional<PaqueteModel> paqueteExistente = paqueteRepo.findById(idPaquete);
            if (paqueteExistente.isPresent()) {
                paqueteRepo.delete(paqueteExistente.get());
            } else {
                System.out.println("[deletePaquete] Paquete no encontrado: " + idPaquete);
            }
        } catch (Exception e) {
            System.out.println("[deletePaquete] Excepción: " + e.getMessage());
        }
    }

    @Override	
    public PaqueteModel updatePaquete(PaqueteModel paquete) {
        try {
            // Verifica que el paquete existe
            Optional<PaqueteModel> paqueteExistente = paqueteRepo.findById(paquete.getIdPaquete());
            if (!paqueteExistente.isPresent()) {
                System.out.println("[updatePaquete] Paquete no encontrado: " + paquete.getIdPaquete());
                return null;
            }

            // Verifica que el camionero existe
            if (paquete.getCamionero() != null) {
                Optional<CamioneroModel> camioneroExistente = camioneroRepo.findById(paquete.getCamionero().getIdCamionero());
                if (camioneroExistente.isPresent()) {
                    paquete.setCamionero(camioneroExistente.get());
                } else {
                    System.out.println("[updatePaquete] Camionero no encontrado: " + paquete.getCamionero().getIdCamionero());
                    return null;
                }
            }

            // Verifica que la dirección existe
            if (paquete.getidDireccion() != null) {
                Optional<DireccionModel> direccionExistente = direccionRepo.findById(paquete.getidDireccion().getIdDireccion());
                if (direccionExistente.isPresent()) {
                    paquete.setidDireccion(direccionExistente.get());
                } else {
                    System.out.println("[updatePaquete] Dirección no encontrada: " + paquete.getidDireccion().getIdDireccion());
                    return null;
                }
            }

            // Actualiza el paquete existente con los nuevos datos
            PaqueteModel paqueteActualizado = paqueteExistente.get();
            paqueteActualizado.setCodigoPaquete(paquete.getCodigoPaquete());
            paqueteActualizado.setDescripcion(paquete.getDescripcion());
            paqueteActualizado.setCamionero(paquete.getCamionero());
            paqueteActualizado.setidDireccion(paquete.getidDireccion());

            return paqueteRepo.save(paqueteActualizado);
        } catch (Exception e) {
            System.out.println("[updatePaquete] Excepción: " + e.getMessage());
            return null;
        }
    }

}