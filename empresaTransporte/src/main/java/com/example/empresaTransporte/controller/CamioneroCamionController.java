package com.example.empresaTransporte.controller;

import com.example.empresaTransporte.model.Camionero_Camion;
import com.example.empresaTransporte.service.CamioneroCamionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/camionero_camion")
public class CamioneroCamionController {

    @Autowired
    private CamioneroCamionService camioneroCamionService;

    @PostMapping("/create")
    public ResponseEntity<Camionero_Camion> crearRelacion(@RequestBody Camionero_Camion camioneroCamion) {
        try {
            Camionero_Camion nuevaRelacion = camioneroCamionService.crearCamioneroCamion(camioneroCamion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaRelacion);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Camionero_Camion>> obtenerTodasLasRelaciones() {
        try {
            List<Camionero_Camion> relaciones = camioneroCamionService.obtenerTodosLosCamionerosCamiones();
            return ResponseEntity.ok(relaciones);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Camionero_Camion> obtenerRelacionPorId(@PathVariable Integer id) {
        try {
            return camioneroCamionService.obtenerCamioneroCamionPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Camionero_Camion> actualizarRelacion(@PathVariable Integer id, @RequestBody Camionero_Camion camioneroCamion) {
        try {
            Camionero_Camion relacionActualizada = camioneroCamionService.actualizarCamioneroCamion(id, camioneroCamion);
            return ResponseEntity.ok(relacionActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarRelacion(@PathVariable Integer id) {
        try {
            camioneroCamionService.eliminarCamioneroCamion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
