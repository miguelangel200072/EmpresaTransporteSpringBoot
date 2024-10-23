package com.example.empresaTransporte.service;

import java.util.List;
import java.util.Optional;

import com.example.empresaTransporte.model.PaqueteModel;

public interface PaqueteService {
    PaqueteModel guardaPaquete(PaqueteModel paquete);
    PaqueteModel getPaqueteByID(Integer id);
    List<PaqueteModel> getAllPaquetes();
    void deletePaqueteByID(Integer id);
    PaqueteModel updatePaquete(PaqueteModel paquete);
}