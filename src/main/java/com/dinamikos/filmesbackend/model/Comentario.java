package com.dinamikos.filmesbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O Atributo Texto é Obrigatório!")
    @Size(max = 5000, message = "O Texto não pode ser maior do que 5000 caracteres")
    private String texto;

    @NotNull(message = "O Atributo Usuário é Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnoreProperties("comentarios")
    private Usuario usuario;

    @NotNull(message = "O Atributo Filme é Obrigatório!")
    @ManyToOne
    @JoinColumn(name = "filme_id")
    @JsonIgnoreProperties("comentarios")
    private Filme filme;

    @ManyToOne
    @JoinColumn(name = "comentario_pai_id")
    @JsonIgnoreProperties("respostas")
    private Comentario comentarioPai;

    @OneToMany(mappedBy = "comentarioPai")
    @JsonIgnoreProperties("comentarioPai")
    private List<Comentario> respostas;

    @NotNull(message = "O Atributo Data do Comentário é Obrigatório!")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataComentario;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public Comentario getComentarioPai() {
        return comentarioPai;
    }

    public void setComentarioPai(Comentario comentarioPai) {
        this.comentarioPai = comentarioPai;
    }

    public List<Comentario> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Comentario> respostas) {
        this.respostas = respostas;
    }

    public Date getDataComentario() {
        return dataComentario;
    }

    public void setDataComentario(Date dataComentario) {
        this.dataComentario = dataComentario;
    }
}
