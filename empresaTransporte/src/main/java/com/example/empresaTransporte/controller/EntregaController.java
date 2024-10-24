package com.example.empresaTransporte.controller;

import com.example.empresaTransporte.model.EntregaModel;
import com.example.empresaTransporte.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private EntregaService entregaService;

    @PostMapping("/create")
    public ResponseEntity<?> crearEntrega(@RequestBody EntregaModel entrega) {
        try {
            EntregaModel nuevaEntrega = entregaService.crearEntrega(entrega);
            return ResponseEntity.ok(nuevaEntrega);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la entrega: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodasLasEntregas() {
        try {
            List<EntregaModel> entregas = entregaService.obtenerTodasLasEntregas();
            return ResponseEntity.ok(entregas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todas las entregas: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerEntregaPorId(@PathVariable Integer id) {
        try {
            Optional<EntregaModel> entrega = entregaService.obtenerEntregaPorId(id);
            if (entrega.isPresent()) {
                return ResponseEntity.ok(entrega.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Entrega no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la entrega por ID: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarEntrega(@PathVariable Integer id, @RequestBody EntregaModel entrega) {
        try {
            EntregaModel entregaActualizada = entregaService.actualizarEntrega(id, entrega);
            return ResponseEntity.ok(entregaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la entrega: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarEntrega(@PathVariable Integer id) {
        try {
            entregaService.eliminarEntrega(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la entrega: " + e.getMessage());
        }
    }
}