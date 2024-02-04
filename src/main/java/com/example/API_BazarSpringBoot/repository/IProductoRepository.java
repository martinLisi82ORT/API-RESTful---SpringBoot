package com.example.API_BazarSpringBoot.repository;

import com.example.API_BazarSpringBoot.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

}
