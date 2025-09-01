package com.spring.cursoapis.service.impl;

import com.spring.cursoapis.entity.Curso;
import com.spring.cursoapis.entity.EstadoCurso;
import com.spring.cursoapis.repository.CursoRepository;
import com.spring.cursoapis.service.CursoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;


    @Override
    public Curso registrarCurso(Curso curso) {
        return cursoRepository.save(curso);

    }

    @Override
    public List<Curso> registrarVariosCuros(List<Curso> curso) {
        return cursoRepository.saveAll(curso);
    }


    @Override
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> BuscarPorNombre(String nombreCurso) {
        return cursoRepository.findByNombreCurso(nombreCurso);
    }

    @Override
    public Optional<Curso> BuscarPorId(Long idCurso) {
        return cursoRepository.findByIdCurso(idCurso);
    }

    @Override
    @SneakyThrows
    public Curso actualizarCurso(Long idCurso, Curso curso) {

        //Si no existe
        Curso cursoExistente = cursoRepository.findByIdCurso(idCurso)
                .orElseThrow(() -> new Exception("Curso con ID " + idCurso + " no encontrado"));

        //Si existe
        cursoExistente.setNombreCurso(curso.getNombreCurso());
        cursoExistente.setDescripcionCurso(curso.getDescripcionCurso());
        cursoExistente.setPrecio(curso.getPrecio());
        cursoExistente.setEstadoCurso(curso.getEstadoCurso());

        return cursoRepository.save(cursoExistente);

    }
    @Override
    @SneakyThrows
    public void eliminarCurso(Long idCurso) {
        cursoRepository.findByIdCurso(idCurso)
                        .orElseThrow(()-> new Exception("Curso con ID " + idCurso + " no encontrado"));

        cursoRepository.deleteById(idCurso);
    }

    @Override
    @SneakyThrows
    public Curso cambiarEstadoCurso(Long idCurso, EstadoCurso nuevoEstadoCurso) {
        Curso cursoexistente = cursoRepository.findByIdCurso(idCurso)
                .orElseThrow(()-> new Exception("Curso con ID " + idCurso + " no encontrado"));
        cursoexistente.setEstadoCurso(nuevoEstadoCurso);
        return cursoRepository.save(cursoexistente);
    }

    @Override
    public List<Curso> listarCursosActivos(EstadoCurso estadoCurso) {
        return cursoRepository.findAllByEstadoCurso(estadoCurso);
    }
}
