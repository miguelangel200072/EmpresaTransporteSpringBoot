package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empresaTransporte.model.UsuarioModel;


public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    UsuarioModel findByUsername(String username);
}