package com.project.task.service;

import com.project.task.model.Project;
import com.project.task.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void createProject(Project project) throws Exception {
        if(!projectRepository.existsByName(project.getName())){
            projectRepository.save(project);
        }
        else{
            throw new Exception("Project is already contain");
        }
    }

    public void saveProject(Project project){ projectRepository.save(project); }

    public void deleteProjectById(Long id){
        projectRepository.deleteById(id);
    }

    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id){
        return projectRepository.getProjectById(id);
    }

    public List<Project> filterByID(Long start, Long end){
        return projectRepository.findByIdBetween(start, end);
    }

    public List<Project> filteredByName(String pattern){
        return projectRepository.findByNameContains(pattern);
    }
}
