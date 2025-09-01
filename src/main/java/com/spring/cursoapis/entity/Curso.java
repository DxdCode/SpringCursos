package com.spring.cursoapis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;

    @Column(name = "nombre_curso", nullable = false, length = 50)
    private String nombreCurso;

    @Column (name = "descripcion_curso", nullable = false,length = 150)
    private String descripcionCurso;

    @Column (name = "precio", nullable = false)
    private Double precio;

    @Enumerated(EnumType.STRING)
    @Column(name="estado_curso", nullable = false)
    private EstadoCurso estadoCurso;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    private Categoria categoria;



}
