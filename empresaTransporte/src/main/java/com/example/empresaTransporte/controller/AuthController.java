package com.example.empresaTransporte.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.empresaTransporte.model.CamioneroModel;
import com.example.empresaTransporte.model.UsuarioModel;
import com.example.empresaTransporte.service.CamioneroService;
import com.example.empresaTransporte.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private CamioneroService camioneroService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioModel usuario) {
        try {
            if (usuario.getRole() == null || usuario.getRole().isEmpty()) {
                return ResponseEntity.badRequest().body("El rol es requerido.");
            }

            // Verificar si se proporciona un idCamionero en el JSON
            if (usuario.getCamionero() != null && usuario.getCamionero().getIdCamionero() != null) {
                Optional<CamioneroModel> camioneroExistente = camioneroService.obtenerCamioneroPorId(usuario.getCamionero().getIdCamionero());

                if (camioneroExistente.isPresent()) {
                    CamioneroModel camionero = camioneroExistente.get();
                    camionero.setUsuario(usuario);
                    usuario.setCamionero(camionero); // Asociar el camionero existente al usuario
                } else {
                    return ResponseEntity.badRequest().body("El idCamionero proporcionado no existe.");
                }
            } else {
                // Crear un nuevo camionero si no se especifica un idCamionero
                CamioneroModel nuevoCamionero = usuario.getCamionero(); // Obtener el camionero del JSON
                nuevoCamionero.setUsuario(usuario); // Establecer la relación bidireccional
                usuario.setCamionero(nuevoCamionero);
            }

            usuarioService.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado exitosamente!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioModel usuario) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Inicio de sesión exitoso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error de autenticación: " + e.getMessage());
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Has cerrado sesión exitosamente.");
    }
}