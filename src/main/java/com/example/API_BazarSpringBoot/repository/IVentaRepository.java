package com.example.API_BazarSpringBoot.repository;

import com.example.API_BazarSpringBoot.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {

}
