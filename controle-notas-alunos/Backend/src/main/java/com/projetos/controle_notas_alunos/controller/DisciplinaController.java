package com.projetos.controle_notas_alunos.controller;


import com.projetos.controle_notas_alunos.dto.MediaDisciplinaResponse;
import com.projetos.controle_notas_alunos.model.Disciplina;
import com.projetos.controle_notas_alunos.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    // Cria uma nova disciplina
    @PostMapping
    public ResponseEntity<Disciplina> salvarDisciplina(@RequestBody Disciplina disciplina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaService.salvarDisciplina(disciplina));
    }

    // Lista todas as disciplinas
    @GetMapping
    public List<Disciplina> listarDisciplinas() {
        return disciplinaService.listarDisciplinas();
    }

    // Busca uma disciplina por ID
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarDisciplina(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.buscarDisciplina(id);
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Atualiza uma disciplina
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizarDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {
        Disciplina updatedDisciplina = disciplinaService.atualizarDisciplina(id, disciplina);
        if (updatedDisciplina != null) {
            return ResponseEntity.ok(updatedDisciplina);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Deleta uma disciplina
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDisciplina(@PathVariable Long id) {
        if (disciplinaService.deletarDisciplina(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    // Calcular media
    @GetMapping("/{disciplinaId}/media")
    public Double getMediaDisciplina(@PathVariable Long disciplinaId) {
        return disciplinaService.calcularMediaDisciplina(disciplinaId);
    }

    // Endpoint para calcular a média do aluno em uma disciplina e retornar todas as informações detalhadas
    @GetMapping("/{disciplinaId}/alunos/{alunoId}/media")
    public MediaDisciplinaResponse getMediaAlunoDisciplina(@PathVariable Long alunoId, @PathVariable Long disciplinaId) {
        return disciplinaService.calcularMediaAlunoDisciplina(alunoId, disciplinaId);
    }
}
