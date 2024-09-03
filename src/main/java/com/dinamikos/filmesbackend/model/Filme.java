package com.dinamikos.filmesbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_filmes")
public class Filme {

    @Id
    private Long id;

    @NotBlank(message = "O Atributo Título é Obrigatório!")
    private String titulo;

    @NotBlank(message = "O Atributo Descrição é Obrigatório!")
    @Size(max = 5000, message = "A Descrição não pode ser maior do que 5000 caracteres")
    private String descricao;

    @NotNull(message = "O Atributo Data de Lançamento é Obrigatório!")
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;

    @NotBlank(message = "O Atributo Gênero é Obrigatório!")
    private String genero;

    @ManyToMany(mappedBy = "filmesSalvos")
    @JsonIgnoreProperties("filmesSalvos")
    private List<Usuario> usuariosQueSalvaram;

    @ManyToMany(mappedBy = "filmesCurtidos")
    @JsonIgnoreProperties("filmesCurtidos")
    private Set<Usuario> usuariosQueCurtiram = new HashSet<>();

    @OneToMany(mappedBy = "filme")
    @JsonIgnoreProperties("filme")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "filme")
    @JsonIgnoreProperties("filme")
    private List<Comentario> comentarios;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(@NotBlank(message = "O Atributo Gênero é Obrigatório!") String genero) {
        this.genero = genero;
    }

    public List<Usuario> getUsuariosQueSalvaram() {
        return usuariosQueSalvaram;
    }

    public void setUsuariosQueSalvaram(List<Usuario> usuariosQueSalvaram) {
        this.usuariosQueSalvaram = usuariosQueSalvaram;
    }

    public Set<Usuario> getUsuariosQueCurtiram() {
        return usuariosQueCurtiram;
    }

    public void setUsuariosQueCurtiram(Set<Usuario> usuariosQueCurtiram) {
        this.usuariosQueCurtiram = usuariosQueCurtiram;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
}
