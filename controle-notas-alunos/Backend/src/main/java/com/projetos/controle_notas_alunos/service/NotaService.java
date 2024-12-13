package com.projetos.controle_notas_alunos.service;

import com.projetos.controle_notas_alunos.model.Nota;
import com.projetos.controle_notas_alunos.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService {

    private final NotaRepository notaRepository;

    @Autowired
    public NotaService( NotaRepository notaRepository){
        this.notaRepository = notaRepository;
    }

    public Nota salvar(Nota nota){
        return notaRepository.save(nota);
    }

    public List<Nota> listarPorAluno(Long alunoId){
        return notaRepository.findByAlunoId(alunoId);
    }
    
}
