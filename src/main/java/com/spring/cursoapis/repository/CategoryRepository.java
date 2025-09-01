package com.spring.cursoapis.repository;

import com.spring.cursoapis.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNombreCategoria(String nombreCategoria);
    boolean existsByNombreCategoria(String nombreCategoria);

}
