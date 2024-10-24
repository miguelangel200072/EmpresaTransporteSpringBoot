package com.example.empresaTransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.empresaTransporte.model.CamionModel;
import com.example.empresaTransporte.service.CamionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/camiones")
public class CamionController {

    @Autowired
    private CamionService camionService;

    @PostMapping("/create")
    public ResponseEntity<?> crearCamion(@RequestBody CamionModel camion) {
        try {
            CamionModel nuevoCamion = camionService.crearCamion(camion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCamion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el camión: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodosLosCamiones() {
        try {
            List<CamionModel> camiones = camionService.obtenerTodosLosCamiones();
            return ResponseEntity.ok(camiones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la lista de camiones: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerCamionPorId(@PathVariable Integer id) {
        try {
            Optional<CamionModel> optionalCamion = camionService.obtenerCamionPorId(id);
            if (optionalCamion.isPresent()) {
                return ResponseEntity.ok(optionalCamion.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Camión no encontrado con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el camión por ID: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarCamion(@PathVariable Integer id, @RequestBody CamionModel camion) {
        try {
            CamionModel camionActualizado = camionService.actualizarCamion(id, camion);
            return ResponseEntity.ok(camionActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el camión: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarCamion(@PathVariable Integer id) {
        try {
            camionService.eliminarCamion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el camión: " + e.getMessage());
        }
    }
}