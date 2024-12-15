package com.projetos.controle_notas_alunos.repository;

import com.projetos.controle_notas_alunos.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
