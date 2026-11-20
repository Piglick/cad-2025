
package com.example.lab2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Отображение главной страницы
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "index";
    }

    // Добавление новой задачи
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/";
    }

    // Удаление задачи
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }

    // Изменение статуса задачи
    @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    @PostMapping("/updateTask/{id}")
    public String updateTask(@PathVariable Long id,
                             @RequestParam boolean completed) {
        taskService.updateTask(id, completed);
        return "redirect:/";
    }
}
