package com.example.empresaTransporte.serviceImpl;

import com.example.empresaTransporte.model.PaqueteModel;
import com.example.empresaTransporte.model.RecogidaModel;
import com.example.empresaTransporte.repository.PaqueteRepository;
import com.example.empresaTransporte.repository.RecogidaRepository;
import com.example.empresaTransporte.service.RecogidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RecogidaServiceImpl implements RecogidaService {

    @Autowired
    private RecogidaRepository recogidaRepository;

    @Autowired
    private PaqueteRepository paqueteRepository;

    @Override
    public RecogidaModel crearRecogida(RecogidaModel recogida) {
        // Verificar si el paquete asociado existe
        Optional<PaqueteModel> paqueteOpt = paqueteRepository.findById(recogida.getPaquete().getIdPaquete());
        if (paqueteOpt.isPresent()) {
            recogida.setPaquete(paqueteOpt.get());
            // Si la fecha es null, establecer la fecha actual
            if(recogida.getFechaRecogida() == null) {
            	recogida.setFechaRecogida(LocalDate.now());
            }
            return recogidaRepository.save(recogida);
        } else {
            throw new RuntimeException("Paquete no encontrado");
        }
    }

    @Override
    public List<RecogidaModel> obtenerTodasLasRecogidas() {
        return recogidaRepository.findAll();
    }

    @Override
    public Optional<RecogidaModel> obtenerRecogidaPorId(Integer id) {
        return recogidaRepository.findById(id);
    }

    @Override
    public RecogidaModel actualizarRecogida(Integer id, RecogidaModel recogida) {
        if (recogidaRepository.existsById(id)) {
            // Verificar si el paquete asociado existe
            Optional<PaqueteModel> paqueteOpt = paqueteRepository.findById(recogida.getPaquete().getIdPaquete());
            if (paqueteOpt.isPresent()) {
                recogida.setPaquete(paqueteOpt.get());
                recogida.setIdRecogida(id);
                // Si la fecha es null, establecer la fecha actual
                if(recogida.getFechaRecogida() == null) {
                	recogida.setFechaRecogida(LocalDate.now());
                }
                return recogidaRepository.save(recogida);
            } else {
                throw new RuntimeException("Paquete no encontrado");
            }
        }
        throw new RuntimeException("Recogida no encontrada");
    }

    @Override
    public void eliminarRecogida(Integer id) {
        if (recogidaRepository.existsById(id)) {
            recogidaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Recogida no encontrada");
        }
    }
}
