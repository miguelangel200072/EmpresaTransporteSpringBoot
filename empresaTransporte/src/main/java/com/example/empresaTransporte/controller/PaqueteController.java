package com.example.empresaTransporte.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.empresaTransporte.model.PaqueteModel;
import com.example.empresaTransporte.service.PaqueteService;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    @Autowired
    private PaqueteService paqueteService;

    @PostMapping("/create")
    public ResponseEntity<?> crearPaquete(@RequestBody PaqueteModel paquete) {
        try {
            PaqueteModel nuevoPaquete = paqueteService.guardaPaquete(paquete);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaquete);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el paquete: " + e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> obtenerTodosLosPaquetes() {
        try {
            List<PaqueteModel> paquetes = paqueteService.getAllPaquetes();
            return ResponseEntity.ok(paquetes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todos los paquetes: " + e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPaqueteById(@PathVariable int id) {
        try {
            PaqueteModel paquete = paqueteService.getPaqueteByID(id);
            if (paquete != null) {
                return ResponseEntity.ok(paquete);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Paquete no encontrado con ID: " + id);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el paquete por ID: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePaquete(@RequestBody PaqueteModel paquete) {
        try {
            PaqueteModel paqueteActualizado = paqueteService.updatePaquete(paquete);
            if (paqueteActualizado != null) {
                return ResponseEntity.ok(paqueteActualizado);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error al actualizar el paquete. Verifica los datos proporcionados.");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el paquete: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> eliminarPaquete(@PathVariable Integer id) {
        try {
            paqueteService.deletePaqueteByID(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el paquete: " + e.getMessage());
        }
    }
}