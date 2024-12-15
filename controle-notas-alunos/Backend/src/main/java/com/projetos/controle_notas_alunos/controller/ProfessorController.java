package com.projetos.controle_notas_alunos.controller;

import com.projetos.controle_notas_alunos.model.Professor;
import com.projetos.controle_notas_alunos.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    // Cria um novo professor
    @PostMapping
    public ResponseEntity<Professor> salvarProfessor(@RequestBody Professor professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professorService.salvarProfessor(professor));
    }

    // Lista todos os professores
    @GetMapping
    public List<Professor> listarProfessores() {
        return professorService.listarProfessores();
    }

    // Busca um professor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessor(@PathVariable Long id) {
        Professor professor = professorService.buscarProfessor(id);
        if (professor != null) {
            return ResponseEntity.ok(professor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Atualiza um professor
    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor updatedProfessor = professorService.atualizarProfessor(id, professor);
        if (updatedProfessor != null) {
            return ResponseEntity.ok(updatedProfessor);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Deleta um professor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id) {
        if (professorService.deletarProfessor(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
