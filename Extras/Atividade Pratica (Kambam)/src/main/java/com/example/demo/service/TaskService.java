package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        task.setStatus("A Fazer"); // Tarefa sempre começa em "A Fazer"
        task.setCreationDate(java.time.LocalDate.now());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setDeadline(updatedTask.getDeadline());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task moveTask(Long id) {
        return taskRepository.findById(id).map(task -> {
            switch (task.getStatus()) {
                case "A Fazer" -> task.setStatus("Em Progresso");
                case "Em Progresso" -> task.setStatus("Concluído");
                default -> throw new RuntimeException("Task already completed");
            }
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getTasksByStatusOrdered(String status) {
        return taskRepository.findByStatusOrderedByPriority(status);
    }

    public List<Task> filterTasksByPriorityAndDeadline(String priority, LocalDate date) {
        return taskRepository.findByPriorityAndDeadlineBefore(priority, date);
    }

    public List<Task> getOverdueTasks() {
        return taskRepository.findOverdueTasks(LocalDate.now());
    }


}
