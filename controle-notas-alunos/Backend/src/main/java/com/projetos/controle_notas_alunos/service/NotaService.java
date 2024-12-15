package com.projetos.controle_notas_alunos.service;

import com.projetos.controle_notas_alunos.model.Disciplina;
import com.projetos.controle_notas_alunos.model.Nota;
import com.projetos.controle_notas_alunos.repository.NotaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    // Injeção de dependencia do repositorio
    @Autowired
    public NotaService(NotaRepository notaRepository){
        this.notaRepository = notaRepository;
    }

    // Salva uma nota no banco de dados
    public Nota salvarNota(Nota nota) {
        return notaRepository.save(nota);
    }

    // Lista todas as notas cadastradas
    public List<Nota> listarNotas() {
        return notaRepository.findAll();
    }

    // Busca uma nota por ID
    public Nota buscarNota(Long id) {
        return notaRepository.findById(id).orElse(null);
    }

    // Atualiza uma nota
    public Nota atualizarNota(Long id, Nota nota) {
        if (notaRepository.existsById(id)) {
            nota.setId(id);
            return notaRepository.save(nota);
        }
        return null;
    }

    // Deleta uma nota
    public boolean deletarNota(Long id) {
        if (notaRepository.existsById(id)) {
            notaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Nota> findNotasPorDisciplina(Disciplina disciplina) {
        // Buscar todas as notas associadas a uma disciplina específica
        return notaRepository.findByDisciplina(disciplina);
    }

    // Método para buscar as notas de um aluno em uma disciplina
    public List<Nota> findNotasPorAlunoEDisciplina(Long alunoId, Disciplina disciplina) {
        // Busca as notas associadas a um aluno e uma disciplina
        return notaRepository.findByAlunoIdAndDisciplina(alunoId, disciplina);
    }
}
