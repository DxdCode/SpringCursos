package com.spring.cursoapis.service.impl;

import com.spring.cursoapis.entity.Categoria;
import com.spring.cursoapis.repository.CategoryRepository;
import com.spring.cursoapis.service.CategoryService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @SneakyThrows
    public Categoria crearCategoria(Categoria categoria) {
        if(categoryRepository.existsByNombreCategoria(categoria.getNombreCategoria())){
            throw new RuntimeException("Categoria existente");
        }
        return categoryRepository.save(categoria);

    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Categoria> listarCategoriaId(Long idCategoria) {
        return categoryRepository.findById(idCategoria);
    }

    @Override
    public Categoria actualizarCategoria(Long idCategoria, Categoria categoria) {
        Categoria actualizarCategoria = categoryRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        actualizarCategoria.setNombreCategoria(categoria.getNombreCategoria());
        return categoryRepository.save(actualizarCategoria);
    }


    @Override
    public void eliminarCategoria(Long idCategoria) {
        categoryRepository.findById(idCategoria)
                        .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        categoryRepository.deleteById(idCategoria);
    }
}
