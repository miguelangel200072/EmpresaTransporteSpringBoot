package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empresaTransporte.model.CamioneroModel;

public interface CamioneroRepository extends JpaRepository<CamioneroModel, Integer> {

}
