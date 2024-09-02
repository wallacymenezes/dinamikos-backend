package com.dinamikos.filmesbackend.repository;

import com.dinamikos.filmesbackend.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
