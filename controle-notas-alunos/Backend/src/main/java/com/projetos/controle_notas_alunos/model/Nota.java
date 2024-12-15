package com.projetos.controle_notas_alunos.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valor;

    @Column
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "avaliacao_id")
    private Avaliacao avaliacao;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    //Construtor
    public Nota(){}

    public Nota(Double valor, Aluno aluno, Avaliacao avaliacao) {
        this.valor = valor;
        this.aluno = aluno;
        this.avaliacao = avaliacao;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor da nota não pode ser negativo.");
        }
        this.valor = valor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    // Usando equals e hashcode

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(valor, nota.valor) && Objects.equals(descricao, nota.descricao) && Objects.equals(aluno, nota.aluno) && Objects.equals(avaliacao, nota.avaliacao) && Objects.equals(disciplina, nota.disciplina);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, descricao, aluno, avaliacao, disciplina);
    }
}
