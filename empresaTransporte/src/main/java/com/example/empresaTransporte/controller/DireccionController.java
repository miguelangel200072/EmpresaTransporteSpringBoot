package com.example.empresaTransporte.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.empresaTransporte.model.DireccionModel;
import com.example.empresaTransporte.service.DireccionService;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    @Autowired
    private DireccionService direccionService;

    @PostMapping("/create")
    public ResponseEntity<DireccionModel> crearDireccion(@RequestBody DireccionModel direccion) {
        try {
            DireccionModel nuevaDireccion = direccionService.crearDireccion(direccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDireccion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DireccionModel>> obtenerTodasLasDirecciones() {
        try {
            List<DireccionModel> direcciones = direccionService.obtenerTodasLasDirecciones();
            return ResponseEntity.ok(direcciones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DireccionModel> obtenerDireccionPorId(@PathVariable Integer id) {
        try {
            return direccionService.obtenerDireccionPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DireccionModel> actualizarDireccion(@PathVariable Integer id, @RequestBody DireccionModel direccion) {
        try {
            DireccionModel direccionActualizada = direccionService.actualizarDireccion(id, direccion);
            return ResponseEntity.ok(direccionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarDireccion(@PathVariable Integer id) {
        try {
            direccionService.eliminarDireccion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
