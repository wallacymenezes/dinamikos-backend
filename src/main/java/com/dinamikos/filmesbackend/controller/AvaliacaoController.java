package com.dinamikos.filmesbackend.controller;

import com.dinamikos.filmesbackend.model.Avaliacao;
import com.dinamikos.filmesbackend.repository.AvaliacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Avaliacao>> getAll() {
        return ResponseEntity.ok(avaliacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getById(@PathVariable Long id) {
        return avaliacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Avaliacao> post(@RequestBody @Valid Avaliacao avaliacao) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(avaliacaoRepository.save(avaliacao));
    }

    @PutMapping
    public ResponseEntity<Avaliacao> put(@Valid @RequestBody Avaliacao avaliacao) {
        return avaliacaoRepository.findById(avaliacao.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoRepository.save(avaliacao)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);

        if (avaliacao.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        avaliacaoRepository.deleteById(id);
    }
}
