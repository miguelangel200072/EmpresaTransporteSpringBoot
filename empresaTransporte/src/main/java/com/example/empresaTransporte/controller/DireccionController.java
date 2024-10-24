package com.example.empresaTransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.empresaTransporte.model.DireccionModel;
import com.example.empresaTransporte.service.DireccionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @PostMapping("/create")
    public ResponseEntity<?> crearDireccion(@RequestBody DireccionModel direccion) {
        try {
            DireccionModel nuevaDireccion = direccionService.crearDireccion(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDireccion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la dirección: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodasLasDirecciones() {
        try {
            List<DireccionModel> direcciones = direccionService.obtenerTodasLasDirecciones();
            return ResponseEntity.ok(direcciones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todas las direcciones: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerDireccionPorId(@PathVariable Integer id) {
        try {
            Optional<DireccionModel> direccionOpt = direccionService.obtenerDireccionPorId(id);
            if (direccionOpt.isPresent()) {
                return ResponseEntity.ok(direccionOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Dirección no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la dirección por ID: " + e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarDireccion(@PathVariable Integer id, @RequestBody DireccionModel direccion) {
        try {
            DireccionModel direccionActualizada = direccionService.actualizarDireccion(id, direccion);
            if (direccionActualizada != null) {
                return ResponseEntity.ok(direccionActualizada);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al actualizar la dirección. Verifica los datos proporcionados.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la dirección: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarDireccion(@PathVariable Integer id) {
        try {
            direccionService.eliminarDireccion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la dirección: " + e.getMessage());
        }
    }
}
