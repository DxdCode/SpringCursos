package com.spring.cursoapis.service;

import com.spring.cursoapis.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Categoria crearCategoria(Categoria categoria);
    List<Categoria> listarCategorias();

    Optional<Categoria> listarCategoriaId(Long idCategoria);
    Categoria actualizarCategoria(Long idCategoria, Categoria categoria);

    void eliminarCategoria(Long idCategoria);


}
