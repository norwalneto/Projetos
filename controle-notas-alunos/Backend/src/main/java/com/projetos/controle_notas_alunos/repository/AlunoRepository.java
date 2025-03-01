package com.projetos.controle_notas_alunos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.controle_notas_alunos.model.Aluno;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
