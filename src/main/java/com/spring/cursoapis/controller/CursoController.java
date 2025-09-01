package com.spring.cursoapis.controller;

import com.spring.cursoapis.entity.Curso;
import com.spring.cursoapis.entity.EstadoCurso;
import com.spring.cursoapis.service.CursoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")

public class CursoController {


    @Autowired
    private CursoService cursoService;

    @PostMapping("/crear")
    public ResponseEntity<?> registrarCurso(@RequestBody Curso curso){
        Curso crearCurso = cursoService.registrarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(crearCurso);
    }

    @PostMapping("/crear/varios")
    public ResponseEntity<?> registrarVarios(@RequestBody List<Curso> curso){
        List<Curso> crearCursos = cursoService.registrarVariosCuros(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(crearCursos);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursosActivos(){
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }

    @GetMapping("/buscar/curso/{nombre}")
    public ResponseEntity<?> buscarCurso(@PathVariable String nombre){
        Optional<Curso> curso = cursoService.BuscarPorNombre(nombre);
        return curso.isPresent()
                ? ResponseEntity.ok(curso.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
    }

    @GetMapping("/buscar/id/{idcurso}")
    public ResponseEntity<?> buscarCursoId(@PathVariable Long idcurso){
        Optional<Curso> curso = cursoService.BuscarPorId(idcurso);
        return curso.isPresent()
                ? ResponseEntity.ok(curso.get())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");

    }

    @PutMapping("/actualizar/{idcurso}")
    public ResponseEntity<?> editarCurso(@PathVariable Long idcurso, @RequestBody Curso curso){
        try{
            Curso cursoActualizado =  new Curso();
            cursoActualizado.setIdCurso(idcurso);
            cursoActualizado.setNombreCurso(curso.getNombreCurso());
            cursoActualizado.setDescripcionCurso(curso.getDescripcionCurso());
            cursoActualizado.setPrecio(curso.getPrecio());
            cursoActualizado.setEstadoCurso(curso.getEstadoCurso());

            Curso cursoBDD = cursoService.actualizarCurso(idcurso, cursoActualizado);
            return  ResponseEntity.status(HttpStatus.OK).body(cursoBDD);

        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idcurso}")
    public ResponseEntity<?> eliminarCurso(@PathVariable Long idcurso){
        try{
             cursoService.eliminarCurso(idcurso);
            return ResponseEntity.ok("Eliminado Correctamente");
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }

    }

    @PutMapping("/estado/{idCurso}")
    public ResponseEntity<?> cambiarEstadoCurso(@PathVariable Long idCurso, @RequestBody EstadoCurso estadoCurso ){

        try{
            Curso cursoActualizado =  cursoService.cambiarEstadoCurso(idCurso, estadoCurso);
            return  ResponseEntity.status(HttpStatus.OK).body(cursoActualizado);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarEstado(@PathVariable EstadoCurso estado){
        List<Curso> cursos = cursoService.listarCursosActivos(estado);
        return ResponseEntity.status(HttpStatus.OK).body(cursos);
    }
}
