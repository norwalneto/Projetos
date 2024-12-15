package com.projetos.controle_notas_alunos.service;

import com.projetos.controle_notas_alunos.model.Avaliacao;
import com.projetos.controle_notas_alunos.repository.AvaliacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    @Autowired
    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    // Salva uma avaliação no banco de dados
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    // Lista todas as avaliações cadastradas
    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    // Busca uma avaliação por ID
    public Avaliacao buscarAvaliacao(Long id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

    // Atualiza uma avaliação
    public Avaliacao atualizarAvaliacao(Long id, Avaliacao avaliacao) {
        if (avaliacaoRepository.existsById(id)) {
            avaliacao.setId(id);
            return avaliacaoRepository.save(avaliacao);
        }
        return null;
    }

    // Deleta uma avaliação
    public boolean deletarAvaliacao(Long id) {
        if (avaliacaoRepository.existsById(id)) {
            avaliacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
