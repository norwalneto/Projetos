package com.projetos.controle_notas_alunos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.controle_notas_alunos.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{
    List<Nota> findByAlunoId(Long alunoId);
    
}
