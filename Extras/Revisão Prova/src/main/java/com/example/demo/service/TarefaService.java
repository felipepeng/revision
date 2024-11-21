package com.example.demo.service;

import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private final TarefaRepository tarefaRepository;

    public TarefaService(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    //Get
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    //Post
    public Tarefa criarTarefa(Tarefa tarefa) {
        tarefa.setDatacriacao(java.time.LocalDate.now());
        return tarefaRepository.save(tarefa);
    }

    //Put
    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        // Busca a tarefa existente no repositório pelo ID
        return tarefaRepository.findById(id)
                .map(tarefaExistente -> {
                    tarefaExistente.setNome(tarefaAtualizada.getNome());
                    tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
                    // Adicione outros campos, se necessário
                    return tarefaRepository.save(tarefaExistente); // Salva as alterações
                })
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }

    //Delete
    public void deletarTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }




}
