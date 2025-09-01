package com.spring.cursoapis.controller;

import com.spring.cursoapis.entity.Categoria;
import com.spring.cursoapis.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoryService.crearCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> listaCategoria = categoryService.listarCategorias();
        return ResponseEntity.status(HttpStatus.OK).body(listaCategoria);
    }

    @GetMapping("/{idCategoria}")
    public ResponseEntity<Categoria> obtenerCategoriaId(@PathVariable Long idCategoria) {
        Categoria categoria = categoryService.listarCategoriaId(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long idCategoria ,@RequestBody Categoria categoria) {
        Categoria categoriaActualizada = categoryService.actualizarCategoria(idCategoria, categoria);
        return ResponseEntity.ok(categoriaActualizada);
    }

    @DeleteMapping("eliminar/{idCategoria}")
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Long idCategoria) {
        try{
            categoryService.eliminarCategoria(idCategoria);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception ex){
            return ResponseEntity.badRequest().build();
        }

    }
}
