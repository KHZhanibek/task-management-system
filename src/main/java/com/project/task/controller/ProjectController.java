package com.project.task.controller;

import com.project.task.model.Project;
import com.project.task.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/examples")
@Api(tags = "Project Resource", value = "Project resources")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation("Show main page")
    @RequestMapping(value="/", method = RequestMethod.GET, produces = "application/json")
    public String getMain(Model model){
        model.addAttribute("projects", projectService.getProjects());
        return "index";
    }

    @ApiOperation("Show project form")
    @RequestMapping(value="/project", method = RequestMethod.GET, produces = "application/json")
    public String addProjectView(Model model){
        Project project = new Project();
        model.addAttribute("project", project);
        return "project_form";
    }

    @ApiOperation("Add project")
    @RequestMapping(value="/project", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String addProject(Project project) throws Exception {
        projectService.createProject(project);
        return "redirect:/";
    }

    @ApiOperation("Update project by id")
    @RequestMapping(value="/project/{id}", method = RequestMethod.PUT)
    public String updateProjectView(@PathVariable ( value = "id") long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "project_update";
    }

    @ApiOperation("Delete project by id")
    @RequestMapping(value="/project/{id}", method = RequestMethod.DELETE)
    public String deleteProjectView(@PathVariable (value = "id") long id) {
        projectService.deleteProjectById(id);
        return "redirect:/";
    }

    @ApiOperation("Filter projects by ID")
    @RequestMapping(value="/project/filter/id", method = RequestMethod.POST)
    public String getFilteredByID(@RequestParam("startRange") Long start, @RequestParam("endRange") Long end, Model model){
        model.addAttribute("projects", projectService.filterByID(start, end));
        return "index";
    }

    @ApiOperation("Filter projects by name")
    @RequestMapping(value="/projects/filter/name", method = RequestMethod.POST)
    public String getFilteredByName(@RequestParam("name") String name, Model model){
        model.addAttribute("projects", projectService.filteredByName(name));
        return "index";
    }

    @ApiOperation("Save project in database")
    @RequestMapping(value="/project/save", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public String saveProject(@ModelAttribute("project") Project project) {
        projectService.saveProject(project);
        return "redirect:/";
    }
}
