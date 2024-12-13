package com.projetos.controle_notas_alunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.controle_notas_alunos.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
}
