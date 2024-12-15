package com.projetos.controle_notas_alunos.service;

import com.projetos.controle_notas_alunos.model.Aluno;
import com.projetos.controle_notas_alunos.repository.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Salva um aluno no banco de dados
    public Aluno salvarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // Lista todos os alunos cadastrados
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }

    // Busca um aluno por ID
    public Aluno buscarAluno(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    // Atualiza um aluno
    public Aluno atualizarAluno(Long id, Aluno aluno) {
        if (alunoRepository.existsById(id)) {
            aluno.setId(id);
            return alunoRepository.save(aluno);
        }
        return null;
    }

    // Deleta um aluno
    public boolean deletarAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
