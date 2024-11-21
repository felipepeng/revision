package com.example.demo.controller;

import com.example.demo.model.Tarefa;
import com.example.demo.service.TarefaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaService tarefaService;

    public TarefaController(TarefaService produtoService) {
        this.tarefaService = produtoService;
    }

    @GetMapping
    public List<Tarefa> listarTodos() {
        return tarefaService.listarTarefas();
    }

    @PostMapping
    public Tarefa salvar(@RequestBody Tarefa tarefa) {
        return tarefaService.criarTarefa(tarefa);
    }

    /*
    {
    "id": 2,
    "nome": "Catar coquinho",
    "descricao": "Catar little cocos",
    "datacriacao": "2024-11-21"
}
    */

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaService.atualizarTarefa(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}