package com.example.empresaTransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.empresaTransporte.model.CamioneroModel;
import com.example.empresaTransporte.service.CamioneroService;

import java.util.List;

@RestController
@RequestMapping("/api/camioneros")
public class CamioneroController {

    @Autowired
    private CamioneroService camioneroService;

    @PostMapping("/create")
    public ResponseEntity<CamioneroModel> crearCamionero(@RequestBody CamioneroModel camionero) {
        try {
            CamioneroModel nuevoCamionero = camioneroService.crearCamionero(camionero);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCamionero);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CamioneroModel>> obtenerTodosLosCamioneros() {
        try {
            List<CamioneroModel> camioneros = camioneroService.obtenerTodosLosCamioneros();
            return ResponseEntity.ok(camioneros);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CamioneroModel> obtenerCamioneroPorId(@PathVariable Integer id) {
        try {
            return camioneroService.obtenerCamioneroPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CamioneroModel> actualizarCamionero(@PathVariable Integer id, @RequestBody CamioneroModel camionero) {
        try {
            CamioneroModel camioneroActualizado = camioneroService.actualizarCamionero(id, camionero);
            return ResponseEntity.ok(camioneroActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarCamionero(@PathVariable Integer id) {
        try {
            camioneroService.eliminarCamionero(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
