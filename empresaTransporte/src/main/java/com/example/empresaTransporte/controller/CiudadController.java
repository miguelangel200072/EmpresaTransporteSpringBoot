package com.example.empresaTransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.empresaTransporte.model.CiudadModel;
import com.example.empresaTransporte.service.CiudadService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    @Autowired
    private CiudadService ciudadService;

    @PostMapping("/create")
    public ResponseEntity<?> crearCiudad(@RequestBody CiudadModel ciudad) {
        try {
            CiudadModel nuevaCiudad = ciudadService.crearCiudad(ciudad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la ciudad: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodasLasCiudades() {
        try {
            List<CiudadModel> ciudades = ciudadService.obtenerTodasLasCiudades();
            return ResponseEntity.ok(ciudades);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todas las ciudades: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> obtenerCiudadPorId(@PathVariable Integer id) {
        try {
            Optional<CiudadModel> ciudadOpt = ciudadService.obtenerCiudadPorId(id);
            if (ciudadOpt.isPresent()) {
                return ResponseEntity.ok(ciudadOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ciudad no encontrada con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la ciudad por ID: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> actualizarCiudad(@PathVariable Integer id, @RequestBody CiudadModel ciudad) {
        try {
            CiudadModel ciudadActualizada = ciudadService.actualizarCiudad(id, ciudad);
            return ResponseEntity.ok(ciudadActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la ciudad: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarCiudad(@PathVariable Integer id) {
        try {
            ciudadService.eliminarCiudad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la ciudad: " + e.getMessage());
        }
    }
}