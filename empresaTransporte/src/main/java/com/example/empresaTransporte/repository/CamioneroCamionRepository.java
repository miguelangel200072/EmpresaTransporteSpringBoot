package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.empresaTransporte.model.Camionero_Camion;

public interface CamioneroCamionRepository extends JpaRepository<Camionero_Camion, Integer> {
}
