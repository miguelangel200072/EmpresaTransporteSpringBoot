package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empresaTransporte.model.CamionModel;

public interface CamionRepository extends JpaRepository<CamionModel, Integer> {
}
