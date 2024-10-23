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
    public ResponseEntity<CiudadModel> crearCiudad(@RequestBody CiudadModel ciudad) {
        try {
            CiudadModel nuevaCiudad = ciudadService.crearCiudad(ciudad);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCiudad);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CiudadModel>> obtenerTodasLasCiudades() {
        try {
            List<CiudadModel> ciudades = ciudadService.obtenerTodasLasCiudades();
            return ResponseEntity.ok(ciudades);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CiudadModel> obtenerCiudadPorId(@PathVariable Integer id) {
        try {
            return ciudadService.obtenerCiudadPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CiudadModel> actualizarCiudad(@PathVariable Integer id, @RequestBody CiudadModel ciudad) {
        try {
            CiudadModel ciudadActualizada = ciudadService.actualizarCiudad(id, ciudad);
            return ResponseEntity.ok(ciudadActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarCiudad(@PathVariable Integer id) {
        try {
            ciudadService.eliminarCiudad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}