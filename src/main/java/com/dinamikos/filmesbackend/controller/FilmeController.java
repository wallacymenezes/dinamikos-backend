package com.dinamikos.filmesbackend.controller;

import com.dinamikos.filmesbackend.model.Filme;
import com.dinamikos.filmesbackend.repository.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Filme>> getAll() {
        return ResponseEntity.ok(filmeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> getById(@PathVariable Long id) {
        return filmeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Filme> post(@RequestBody @Valid Filme filme) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(filmeRepository.save(filme));
    }

    @PutMapping
    public ResponseEntity<Filme> put(@Valid @RequestBody Filme filme) {
        return filmeRepository.findById(filme.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(filmeRepository.save(filme)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);

        if (filme.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        filmeRepository.deleteById(id);
    }
}
