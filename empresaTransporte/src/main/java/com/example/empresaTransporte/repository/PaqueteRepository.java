package com.example.empresaTransporte.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.empresaTransporte.model.PaqueteModel;

public interface PaqueteRepository extends JpaRepository<PaqueteModel, Integer> {

}
