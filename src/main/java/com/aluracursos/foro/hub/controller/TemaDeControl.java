package com.aluracursos.foro.hub.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spring.foro_hub.dto.DTOActualizaTema;
import spring.foro_hub.dto.DTODetalleTema;
import spring.foro_hub.dto.DTOListaTema;
import spring.foro_hub.dto.DTORegistroTema;
import spring.foro_hub.model.Tema;
import spring.foro_hub.repository.TemaRepository;

@RestController
@RequestMapping("/temas")
@SecurityRequirement(name = "bearer-key")

public class TemaDeControl {

    @Autowired
    private TemaRepository temaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTema(@RequestBody @Valid DTORegistroTema dtoRegistroTema,
                                        UriComponentsBuilder uriComponentsBuilder) {
        var tema   = new Tema(dtoRegistroTema);
        temaRepository.save(tema);

        var uri = uriComponentsBuilder.path("/temas/{id}").buildAndExpand(tema.getId()).toUri();
        return ResponseEntity.created(uri).body(new DTODetalleTema(tema));
    }

    @GetMapping
    public ResponseEntity<Page<DTOListaTema>> datosListaTema(@PageableDefault(size = 5, sort = {"curso"}) Pageable pageable) {
        var page = temaRepository.findAll(pageable).map(DTOListaTema::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTema(@RequestBody @Valid DTOActualizaTema dtoActualizaTema) {
        var tema = temaRepository.getReferenceById(dtoActualizaTema.id());
        tema.actualizarInformacion(dtoActualizaTema);
        return ResponseEntity.ok(new DTODetalleTema(tema));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTema(@PathVariable Long id) {
        Tema tema = temaRepository.findById(id).orElse(null);
        if (tema == null) {
            return ResponseEntity.noContent().build();
        }
        temaRepository.delete(tema);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTema(@PathVariable Long id) {
        var tema = temaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DTODetalleTema(tema));
    }
}
