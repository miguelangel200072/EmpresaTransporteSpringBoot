package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empresaTransporte.model.CiudadModel;

public interface CiudadRepository extends JpaRepository<CiudadModel, Integer> {
}