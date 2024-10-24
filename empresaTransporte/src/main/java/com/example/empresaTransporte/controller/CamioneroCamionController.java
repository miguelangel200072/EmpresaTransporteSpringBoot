package com.example.empresaTransporte.controller;

import com.example.empresaTransporte.model.Camionero_Camion;
import com.example.empresaTransporte.service.CamioneroCamionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/camionero_camion")
public class CamioneroCamionController {

    @Autowired
    private CamioneroCamionService camioneroCamionService;

    @PostMapping("/create")
    public ResponseEntity<?> crearRelacion(@RequestBody Camionero_Camion camioneroCamion) {
        try {
            Camionero_Camion nuevaRelacion = camioneroCamionService.crearCamioneroCamion(camioneroCamion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRelacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la relación camionero-camión: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodasLasRelaciones() {
        try {
            List<Camionero_Camion> relaciones = camioneroCamionService.obtenerTodosLosCamionerosCamiones();
            return ResponseEntity.ok(relaciones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener las relaciones camionero-camión: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerRelacionPorId(@PathVariable Integer id) {
        try {
            Optional<Camionero_Camion> relacion = camioneroCamionService.obtenerCamioneroCamionPorId(id);
            if (relacion.isPresent()) {
                return ResponseEntity.ok(relacion.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Relación no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la relación por ID: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarRelacion(@PathVariable Integer id, @RequestBody Camionero_Camion camioneroCamion) {
        try {
            Camionero_Camion relacionActualizada = camioneroCamionService.actualizarCamioneroCamion(id, camioneroCamion);
            return ResponseEntity.ok(relacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la relación camionero-camión: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarRelacion(@PathVariable Integer id) {
        try {
            camioneroCamionService.eliminarCamioneroCamion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la relación camionero-camión: " + e.getMessage());
        }
    }
}