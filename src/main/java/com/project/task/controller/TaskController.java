package com.project.task.controller;

import com.project.task.model.Project;
import com.project.task.model.Task;
import com.project.task.service.ProjectService;
import com.project.task.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/examples")
@Api(tags = "Task Resource", value = "Task resources")
public class TaskController {
    private final ProjectService projectService;
    private final TaskService taskService;

    public TaskController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @ApiOperation("Get tasks related to project")
    @RequestMapping(value="/project/{id}/tasks", method = RequestMethod.GET)
    public String getProjectTasks(@PathVariable(value = "id") long id, Model model){
        Project project = projectService.getProjectById(id);
        model.addAttribute("project_id", project.getId());
        model.addAttribute("tasks", taskService.getTasksByProject(project));
        return "project_task_list";
    }

    @ApiOperation("Add task to project")
    @RequestMapping(value="/project/{id}/task", method = RequestMethod.POST)
    public String addTask(Task task){
        taskService.saveTask(task);
        return "redirect:/";
    }

    @ApiOperation("Save task into database")
    @RequestMapping(value="/project/{project_id}/task/{id}", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String saveTask(@ModelAttribute("task") Task task, @PathVariable String task_id) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @ApiOperation("Shows task by id to update")
    @RequestMapping(value="/project/{project_id}/task/{id}", method = RequestMethod.PUT)
    public String updateTask(@PathVariable(value = "id") long id, @PathVariable String project_id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task_update";
    }

    @ApiOperation("Delete task by id")
    @RequestMapping(value="/project/{project_id}/task/{id}", method = RequestMethod.DELETE)
    public String deleteTask(@PathVariable(value = "id") long id, @PathVariable String project_id){
        taskService.deleteTaskById(id);
        return "redirect:/";
    }
}
