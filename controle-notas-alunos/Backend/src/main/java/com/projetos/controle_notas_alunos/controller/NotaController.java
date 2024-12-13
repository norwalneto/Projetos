package com.projetos.controle_notas_alunos.controller;

import com.projetos.controle_notas_alunos.model.Nota;
import com.projetos.controle_notas_alunos.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    @Autowired
    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public Nota salvar(@RequestBody Nota nota) {
        return notaService.salvar(nota);
    }

    @GetMapping("/aluno/{alunoId}")
    public List<Nota> listarPorAluno(@PathVariable Long alunoId) {
        return notaService.listarPorAluno(alunoId);
    }
}
