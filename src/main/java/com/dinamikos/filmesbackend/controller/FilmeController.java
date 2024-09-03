package com.dinamikos.filmesbackend.controller;

import com.dinamikos.filmesbackend.model.Filme;
import com.dinamikos.filmesbackend.model.Usuario;
import com.dinamikos.filmesbackend.repository.FilmeRepository;
import com.dinamikos.filmesbackend.repository.UsuarioRepository;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @PostMapping("/{usuarioId}/curtir/{filmeId}")
    public ResponseEntity<String> curtirFilme(@PathVariable Long usuarioId, @PathVariable Long filmeId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Filme> filmeOpt = filmeRepository.findById(filmeId);

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        if (filmeOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        Filme filme = filmeOpt.get();

        usuario.getFilmesCurtidos().add(filme);
        filme.getUsuariosQueCurtiram().add(usuario);

        usuarioRepository.save(usuario);
        filmeRepository.save(filme);

        return ResponseEntity.status(HttpStatus.OK).body("Filme curtido com sucesso");
    }

    @PostMapping("/salvar")
    public ResponseEntity<Filme> salvarFilme(@RequestBody @Valid Filme filme) {
        // Verifica se o filme já está no banco de dados
        if (filmeRepository.existsById(filme.getId())) {
            return ResponseEntity.status(HttpStatus.OK).body(filmeRepository.findById(filme.getId()).get());
        }

        // Salva o filme no banco de dados se não existir
        Filme savedFilme = filmeRepository.save(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilme);
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
        if (!filmeRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        filmeRepository.deleteById(id);
    }
}
