package com.dinamikos.filmesbackend.controller;

import com.dinamikos.filmesbackend.model.Comentario;
import com.dinamikos.filmesbackend.repository.ComentarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Comentario>> getAll() {
        return ResponseEntity.ok(comentarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getById(@PathVariable Long id) {
        return comentarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comentario> post(@RequestBody @Valid Comentario comentario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comentarioRepository.save(comentario));
    }

    @PutMapping
    public ResponseEntity<Comentario> put(@Valid @RequestBody Comentario comentario) {
        return comentarioRepository.findById(comentario.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(comentario)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioRepository.findById(id);

        if(comentario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        comentarioRepository.deleteById(id);
    }
}
