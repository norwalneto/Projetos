package com.projetos.controle_notas_alunos.controller;

import com.projetos.controle_notas_alunos.model.Nota;
import com.projetos.controle_notas_alunos.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotaController {

    private final NotaService notaService;

    // Injeção de dependencia Service
    @Autowired
    public NotaController(NotaService notaService){
        this.notaService = notaService;
    }

    // Cria uma nova nota
    @PostMapping
    public ResponseEntity<Nota> salvarNota(@RequestBody Nota nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.salvarNota(nota));
    }

    // Lista todas as notas
    @GetMapping
    public List<Nota> listarNotas() {
        return notaService.listarNotas();
    }

    // Busca uma nota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Nota> buscarNota(@PathVariable Long id) {
        Nota nota = notaService.buscarNota(id);
        if (nota != null) {
            return ResponseEntity.ok(nota);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Atualiza uma nota
    @PutMapping("/{id}")
    public ResponseEntity<Nota> atualizarNota(@PathVariable Long id, @RequestBody Nota nota) {
        Nota updatedNota = notaService.atualizarNota(id, nota);
        if (updatedNota != null) {
            return ResponseEntity.ok(updatedNota);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Deleta uma nota
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota(@PathVariable Long id) {
        if (notaService.deletarNota(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
