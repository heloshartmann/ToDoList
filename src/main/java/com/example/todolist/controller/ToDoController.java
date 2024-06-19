package com.example.todolist.controller;

import com.example.todolist.model.ToDo;
import com.example.todolist.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class ToDoController {
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/assessment")
    public String index() {
        return "index";
    }

    @GetMapping
    public List<ToDo> listarTarefas() {
        return toDoRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping
    public ToDo criarTarefa(@RequestBody ToDo tarefa) {
        tarefa.setDataCriacao(new Date());
        return toDoRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ToDo atualizarTarefa(@PathVariable Long id, @RequestBody ToDo tarefaAtualizado) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo.isPresent()) {
            ToDo tarefa = optionalToDo.get();
            tarefa.setTitulo(tarefaAtualizado.getTitulo());
            tarefa.setDescricao(tarefaAtualizado.getDescricao());
            tarefa.setConcluida(tarefaAtualizado.isConcluida());
            return toDoRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa não encontrada para o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        toDoRepository.deleteById(id);
    }
}
