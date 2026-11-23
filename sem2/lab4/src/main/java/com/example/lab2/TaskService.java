
package com.example.lab2;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Добавление новой задачи
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    // Получение всех задач
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Удаление задачи по ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Изменение статуса задачи
    public void updateTask(Long id, boolean completed) {
        Task task = taskRepository.findById(id)
                .orElseThrow();

        task.setCompleted(completed);
        taskRepository.save(task);
    }
}
