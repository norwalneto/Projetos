package com.projetos.controle_notas_alunos.service;

import com.projetos.controle_notas_alunos.model.Professor;
import com.projetos.controle_notas_alunos.repository.ProfessorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    // Salva um professor no banco de dados
    public Professor salvarProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    // Lista todos os professores cadastrados
    public List<Professor> listarProfessores() {
        return professorRepository.findAll();
    }

    // Busca um professor por ID
    public Professor buscarProfessor(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    // Atualiza um professor
    public Professor atualizarProfessor(Long id, Professor professor) {
        if (professorRepository.existsById(id)) {
            professor.setId(id);
            return professorRepository.save(professor);
        }
        return null;
    }

    // Deleta um professor
    public boolean deletarProfessor(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
