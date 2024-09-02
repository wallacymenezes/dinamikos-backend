package com.dinamikos.filmesbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "tb_avaliacoes")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Atributo Nota é Obrigatório!")
    @Size(min = 1, max = 5, message = "A Nota deve ser entre 1 e 5")
    private Double nota;

    @NotNull(message = "O Atributo Usuário é Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("avaliacoes")
    private Usuario usuario;

    @NotNull(message = "O Atributo Filme é Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonIgnoreProperties("avaliacoes")
    private Filme filme;

    @NotNull(message = "O Atributo Data de Avaliação é Obrigatório!")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAvaliacao;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}
