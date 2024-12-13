package com.projetos.controle_notas_alunos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetos.controle_notas_alunos.model.Aluno;
import com.projetos.controle_notas_alunos.repository.AlunoRepository;

@Service
public class AlunoService {
    

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    public Aluno salvar(Aluno aluno){
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listarTodos(){
        return alunoRepository.findAll();
    }

    public Aluno buscarPorId(Long id){
        return alunoRepository.findById(id).orElse(null);
    }

    public void deletar(Long id){
        alunoRepository.deleteById(id);
    }
}
