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
    public ResponseEntity<PaqueteModel> crearPaquete(@RequestBody PaqueteModel paquete) {
        try {
            PaqueteModel nuevoPaquete = paqueteService.guardaPaquete(paquete);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaquete);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PaqueteModel>> obtenerTodosLosPaquetes() {
        try {
            List<PaqueteModel> paquetes = paqueteService.getAllPaquetes();
            return ResponseEntity.ok(paquetes);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PaqueteModel> getPaqueteById(@PathVariable int id) {
        PaqueteModel paquete = paqueteService.getPaqueteByID(id);
        if (paquete != null) {
            return new ResponseEntity<>(paquete, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<PaqueteModel> updatePaquete(@RequestBody PaqueteModel paquete) {
        PaqueteModel paqueteActualizado = paqueteService.updatePaquete(paquete);
        if (paqueteActualizado != null) {
            return new ResponseEntity<>(paqueteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarPaquete(@PathVariable Integer id) {
        try {
            paqueteService.deletePaqueteByID(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}