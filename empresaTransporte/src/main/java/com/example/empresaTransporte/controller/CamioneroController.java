package com.example.empresaTransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.empresaTransporte.model.CamioneroModel;
import com.example.empresaTransporte.service.CamioneroService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/camioneros")
public class CamioneroController {

    @Autowired
    private CamioneroService camioneroService;

    @PostMapping("/create")
    public ResponseEntity<?> crearCamionero(@RequestBody CamioneroModel camionero) {
        try {
            CamioneroModel nuevoCamionero = camioneroService.crearCamionero(camionero);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCamionero);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el camionero: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodosLosCamioneros() {
        try {
            List<CamioneroModel> camioneros = camioneroService.obtenerTodosLosCamioneros();
            return ResponseEntity.ok(camioneros);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todos los camioneros: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerCamioneroPorId(@PathVariable Integer id) {
        try {
            Optional<CamioneroModel> optionalCamionero = camioneroService.obtenerCamioneroPorId(id);
            if (optionalCamionero.isPresent()) {
                return ResponseEntity.ok(optionalCamionero.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Camionero no encontrado con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el camionero por ID: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarCamionero(@PathVariable Integer id, @RequestBody CamioneroModel camionero) {
        try {
            CamioneroModel camioneroActualizado = camioneroService.actualizarCamionero(id, camionero);
            if (camioneroActualizado != null) {
                return ResponseEntity.ok(camioneroActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al actualizar el camionero. Verifica los datos proporcionados.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el camionero: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarCamionero(@PathVariable Integer id) {
        try {
            camioneroService.eliminarCamionero(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el camionero: " + e.getMessage());
        }
    }
}