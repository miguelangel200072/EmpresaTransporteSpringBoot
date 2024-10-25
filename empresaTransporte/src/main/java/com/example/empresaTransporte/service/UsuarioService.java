package com.example.empresaTransporte.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.empresaTransporte.model.UsuarioModel;

public interface UsuarioService extends UserDetailsService {
    UsuarioModel findByUsername(String username);
    UsuarioModel save(UsuarioModel usuario);
}