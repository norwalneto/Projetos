package com.projetos.controle_notas_alunos.repository;

import com.projetos.controle_notas_alunos.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projetos.controle_notas_alunos.model.Nota;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long>{
    List<Nota> findByDisciplina(Disciplina disciplina);
    List<Nota> findByAlunoIdAndDisciplina(Long alunoId, Disciplina disciplina);

}
