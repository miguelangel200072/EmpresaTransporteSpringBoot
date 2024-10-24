package com.example.empresaTransporte.controller;

import com.example.empresaTransporte.model.RecogidaModel;
import com.example.empresaTransporte.service.RecogidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recogidas")
public class RecogidaController {

    @Autowired
    private RecogidaService recogidaService;

    @PostMapping("/create")
    public ResponseEntity<?> crearRecogida(@RequestBody RecogidaModel recogida) {
        try {
            RecogidaModel nuevaRecogida = recogidaService.crearRecogida(recogida);
            return ResponseEntity.ok(nuevaRecogida);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la recogida: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodasLasRecogidas() {
        try {
            List<RecogidaModel> recogidas = recogidaService.obtenerTodasLasRecogidas();
            return ResponseEntity.ok(recogidas);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todas las recogidas: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerRecogidaPorId(@PathVariable Integer id) {
        try {
            Optional<RecogidaModel> recogida = recogidaService.obtenerRecogidaPorId(id);
            if (recogida.isPresent()) {
                return ResponseEntity.ok(recogida.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Recogida no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la recogida por ID: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarRecogida(@PathVariable Integer id, @RequestBody RecogidaModel recogida) {
        try {
            RecogidaModel recogidaActualizada = recogidaService.actualizarRecogida(id, recogida);
            return ResponseEntity.ok(recogidaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la recogida: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarRecogida(@PathVariable Integer id) {
        try {
            recogidaService.eliminarRecogida(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la recogida: " + e.getMessage());
        }
    }
}