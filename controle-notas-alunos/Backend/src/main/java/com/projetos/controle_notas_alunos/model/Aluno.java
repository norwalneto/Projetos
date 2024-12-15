package com.projetos.controle_notas_alunos.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "alunos")
public class Aluno {
    // Define o ID da tabela e a geração automática do valor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do aluno
    @Column(nullable = false, length = 100) // Campo obrigatório, com limite de 100 caracteres
    private String nome;

    // Matricula do aluno
    @Column(nullable = false, unique = true)
    private String matricula;

    //Email do aluno
    @Column(nullable = false, unique = true, length = 150)  // Campo único no banco de dados
    private String email;

    @ManyToMany
    @JoinTable(
            name = "aluno_disciplina",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id"))
    private List<Disciplina> disciplinas;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id")
    private List<Avaliacao> avaliacoes;


    // Construtor padrão necessário para o JPA
    public Aluno(){}

    // Construtir com paramentros
    public  Aluno(String nome, String email, String matricula){
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    // Métodos para comparação e geração de hash (boas práticas em entidades)

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(id, aluno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
