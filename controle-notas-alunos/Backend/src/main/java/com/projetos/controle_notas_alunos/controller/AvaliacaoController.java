package com.projetos.controle_notas_alunos.controller;

import com.projetos.controle_notas_alunos.model.Avaliacao;
import com.projetos.controle_notas_alunos.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @Autowired
    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    // Cria uma nova avaliação
    @PostMapping
    public ResponseEntity<Avaliacao> salvarAvaliacao(@RequestBody Avaliacao avaliacao) {
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoService.salvarAvaliacao(avaliacao));
    }

    // Lista todas as avaliações
    @GetMapping
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoService.listarAvaliacoes();
    }

    // Busca uma avaliação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacao(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.buscarAvaliacao(id);
        if (avaliacao != null) {
            return ResponseEntity.ok(avaliacao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Atualiza uma avaliação
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        Avaliacao updatedAvaliacao = avaliacaoService.atualizarAvaliacao(id, avaliacao);
        if (updatedAvaliacao != null) {
            return ResponseEntity.ok(updatedAvaliacao);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Deleta uma avaliação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
        if (avaliacaoService.deletarAvaliacao(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
