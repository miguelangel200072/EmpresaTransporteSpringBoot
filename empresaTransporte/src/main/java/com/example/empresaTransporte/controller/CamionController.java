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
    public ResponseEntity<CamionModel> crearCamion(@RequestBody CamionModel camion) {
        try {
            CamionModel nuevoCamion = camionService.crearCamion(camion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCamion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CamionModel>> obtenerTodosLosCamiones() {
        try {
            List<CamionModel> camiones = camionService.obtenerTodosLosCamiones();
            return ResponseEntity.ok(camiones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CamionModel> obtenerCamionPorId(@PathVariable Integer id) {
        try {
            return camionService.obtenerCamionPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CamionModel> actualizarCamion(@PathVariable Integer id, @RequestBody CamionModel camion) {
        try {
            CamionModel camionActualizado = camionService.actualizarCamion(id, camion);
            return ResponseEntity.ok(camionActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarCamion(@PathVariable Integer id) {
        try {
            camionService.eliminarCamion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
