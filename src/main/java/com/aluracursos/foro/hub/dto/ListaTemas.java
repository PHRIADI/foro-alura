package com.aluracursos.foro.hub.dto;

import spring.foro_hub.model.Curso;
import spring.foro_hub.model.Tema;

public record ListaTemas(
        Long id,
        String titulo,
        String mensaje,
        Curso curso
) {

    public ListaTemas(Tema tema) {
        this(tema.getId(), tema.getTitulo(), tema.getMensaje(), tema.getCurso());
    }

}
