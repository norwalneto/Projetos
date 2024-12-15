package com.projetos.controle_notas_alunos.dto;

import com.projetos.controle_notas_alunos.model.Nota;

import java.util.List;

public class MediaDisciplinaResponse {

    private Double media;
    private Integer numeroDeAvaliacoes;
    private List<Nota> notas;

    //Construtores

    public MediaDisciplinaResponse() {}

    public MediaDisciplinaResponse(Double media, Integer numeroDeAvaliacoes, List<Nota> notas) {
        this.media = media;
        this.numeroDeAvaliacoes = numeroDeAvaliacoes;
        this.notas = notas;
    }

    // Getters e Setters


    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Integer getNumeroDeAvaliacoes() {
        return numeroDeAvaliacoes;
    }

    public void setNumeroDeAvaliacoes(Integer numeroDeAvaliacoes) {
        this.numeroDeAvaliacoes = numeroDeAvaliacoes;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
