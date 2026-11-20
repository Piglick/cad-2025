
package com.example.lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Добавление новой задачи
    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    // Получение всех задач
    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    // Удаление задачи по ID
    public void deleteTask(Long id) {
        taskRepository.deleteTask(id);
    }
    // Изменение статуса задачи
    public void updateTask(Long id, boolean completed) {
        for (Task task : taskRepository.getAllTasks()) {
            if (task.getId().equals(id)) {
                task.setCompleted(completed);
                break;
            }
        }
    }

}
