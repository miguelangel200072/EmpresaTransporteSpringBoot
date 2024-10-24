package com.example.empresaTransporte.serviceImpl;

import com.example.empresaTransporte.model.EntregaModel;
import com.example.empresaTransporte.model.PaqueteModel;
import com.example.empresaTransporte.repository.EntregaRepository;
import com.example.empresaTransporte.repository.PaqueteRepository;
import com.example.empresaTransporte.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EntregaServiceImpl implements EntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public EntregaModel crearEntrega(EntregaModel entrega) {
        // Verificar si el paquete asociado existe
        Optional<PaqueteModel> paqueteOpt = paqueteRepository.findById(entrega.getPaquete().getIdPaquete());
        if (paqueteOpt.isPresent()) {
            entrega.setPaquete(paqueteOpt.get());
            // Si la fecha es null, establecer la fecha actual
            if (entrega.getFechaEntrega() == null) {
                entrega.setFechaEntrega(LocalDate.now());
            }
            return entregaRepository.save(entrega);
        } else {
            throw new RuntimeException("Paquete no encontrado");
        }
    }

    @Override
    public List<EntregaModel> obtenerTodasLasEntregas() {
        return entregaRepository.findAll();
    }

    @Override
    public Optional<EntregaModel> obtenerEntregaPorId(Integer id) {
        return entregaRepository.findById(id);
    }

    @Override
    public EntregaModel actualizarEntrega(Integer id, EntregaModel entrega) {
        if (entregaRepository.existsById(id)) {
            // Verificar si el paquete asociado existe
            Optional<PaqueteModel> paqueteOpt = paqueteRepository.findById(entrega.getPaquete().getIdPaquete());
            if (paqueteOpt.isPresent()) {
                entrega.setPaquete(paqueteOpt.get());
                entrega.setIdEntrega(id);
                // Si la fecha es null, establecer la fecha actual
                if (entrega.getFechaEntrega() == null) {
                    entrega.setFechaEntrega(LocalDate.now());
                }
                return entregaRepository.save(entrega);
            } else {
                throw new RuntimeException("Paquete no encontrado");
            }
        }
        throw new RuntimeException("Entrega no encontrada");
    }

    @Override
    public void eliminarEntrega(Integer id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Entrega no encontrada");
        }
    }
}

