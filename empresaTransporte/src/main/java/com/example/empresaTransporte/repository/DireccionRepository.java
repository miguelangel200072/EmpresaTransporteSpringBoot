package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empresaTransporte.model.DireccionModel;

public interface DireccionRepository extends JpaRepository<DireccionModel, Integer> {
}
