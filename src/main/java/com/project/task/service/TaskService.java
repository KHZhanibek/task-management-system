package com.project.task.service;

import com.project.task.model.Project;
import com.project.task.model.Task;
import com.project.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksByProject(Project project){
        return taskRepository.getAllByProject(project);
    }

    public Task getTaskById(Long id){
        return taskRepository.getById(id);
    }

    public void saveTask(Task task){ taskRepository.save(task); }

    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }

}
