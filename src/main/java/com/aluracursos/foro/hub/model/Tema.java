package com.aluracursos.foro.hub.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import spring.foro_hub.dto.DTOActualizaTema;
import spring.foro_hub.dto.DTORegistroTema;
import spring.foro_hub.model.Curso;

import java.time.LocalDateTime;

@Table(name = "temas")
@Entity(name = "Tema")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Tema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;

    @CreationTimestamp
    private LocalDateTime fecha_creacion;

    @Enumerated(EnumType.STRING)
    private
    @NotNull
    @Valid Curso curso;

    public Tema(DTORegistroTema dtoRegistroTema) {
        this.titulo = dtoRegistroTema.titulo();
        this.mensaje = dtoRegistroTema.mensaje();
        this.fecha_creacion = LocalDateTime.now();
        this.curso = dtoRegistroTema.curso();
    }

    public void actualizarInformacion(DTOActualizaTema dtoActualizaTema) {
        if(dtoActualizaTema.titulo() != null) {
            this.titulo = dtoActualizaTema.titulo();
        }

        if(dtoActualizaTema.mensaje() != null) {
            this.mensaje = dtoActualizaTema.mensaje();
        }
    }

}
