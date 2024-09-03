package com.dinamikos.filmesbackend.service;

import com.dinamikos.filmesbackend.model.Filme;
import com.dinamikos.filmesbackend.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public Optional<Filme> salvarFilme(Filme filme) {
        if (filmeRepository.existsById(filme.getId())) {
            return Optional.of(filmeRepository.findById(filme.getId()).get());
        }
        return Optional.of(filmeRepository.save(filme));
    }


}
