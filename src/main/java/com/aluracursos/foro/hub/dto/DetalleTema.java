package com.aluracursos.foro.hub.dto;

import java.time.LocalDateTime;
import spring.foro_hub.model.Curso;
import spring.foro_hub.model.Tema;

public record DetalleTema(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Curso curso
) {

    public DetalleTema(Tema tema) {
        this(tema.getTitulo(), tema.getMensaje(), tema.getFecha_creacion(), tema.getCurso());
    }
}
