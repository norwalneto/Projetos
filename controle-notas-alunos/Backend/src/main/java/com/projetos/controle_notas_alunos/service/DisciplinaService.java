package com.projetos.controle_notas_alunos.service;

import com.projetos.controle_notas_alunos.dto.MediaDisciplinaResponse;
import com.projetos.controle_notas_alunos.model.Disciplina;
import com.projetos.controle_notas_alunos.model.Nota;
import com.projetos.controle_notas_alunos.repository.DisciplinaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final NotaService notaService;

    // Injeção de Dependencias do Repository
    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository, NotaService notaService) {
        this.disciplinaRepository = disciplinaRepository;
        this.notaService = notaService;
    }

    // Salva uma disciplina no banco de dados
    public Disciplina salvarDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    // Lista todas as disciplinas cadastradas
    public List<Disciplina> listarDisciplinas() {
        return disciplinaRepository.findAll();
    }

    // Busca uma disciplina por ID
    public Disciplina buscarDisciplina(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    // Atualiza uma disciplina
    public Disciplina atualizarDisciplina(Long id, Disciplina disciplina) {
        if (disciplinaRepository.existsById(id)) {
            disciplina.setId(id);
            return disciplinaRepository.save(disciplina);
        }
        return null;
    }

    // Deleta uma disciplina
    public boolean deletarDisciplina(Long id) {
        if (disciplinaRepository.existsById(id)) {
            disciplinaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Double calcularMediaDisciplina(Long disciplinaId) {
        // Encontrar a disciplina pelo ID
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(disciplinaId);
        if (disciplinaOptional.isEmpty()) {
            return null; // Se a disciplina não for encontrada, retorna null
        }

        Disciplina disciplina = disciplinaOptional.get();
        List<Nota> notas = notaService.findNotasPorDisciplina(disciplina); // Método que busca as notas por disciplina

        // Calcular a média das notas
        if (notas.isEmpty()) {
            return 0.0; // Se não houver notas, a média é 0
        }

        double somaNotas = 0;
        for (Nota nota : notas) {
            somaNotas += nota.getValor(); // Somar todas as notas
        }

        return somaNotas / notas.size(); // Dividir pela quantidade de notas
    }

    // Metodo para calcular a média de um aluno em uma disciplina e retornar todas as informações detalhadas
    public MediaDisciplinaResponse calcularMediaAlunoDisciplina(Long alunoId, Long disciplinaId) {
        // Encontrar a disciplina pelo ID
        Optional<Disciplina> disciplinaOptional = disciplinaRepository.findById(disciplinaId);
        if (disciplinaOptional.isEmpty()) {
            return null; // Se a disciplina não for encontrada, retorna null
        }

        Disciplina disciplina = disciplinaOptional.get();
        List<Nota> notas = notaService.findNotasPorAlunoEDisciplina(alunoId, disciplina); // Metodo que busca as notas do aluno na disciplina

        // Calcular a média das notas
        if (notas.isEmpty()) {
            return new MediaDisciplinaResponse(0.0, 0, notas); // Se não houver notas, a média é 0
        }

        double somaNotas = 0;
        for (Nota nota : notas) {
            somaNotas += nota.getValor(); // Somar todas as notas
        }

        Double media = somaNotas / notas.size(); // Dividir pela quantidade de notas

        // Criar e retornar a resposta detalhada
        return new MediaDisciplinaResponse(media, notas.size(), notas);
    }

}
