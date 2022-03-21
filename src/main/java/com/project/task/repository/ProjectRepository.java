package com.project.task.repository;

import com.project.task.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    boolean existsByName(String name);
    Project getProjectById(Long id);
    List<Project> findByIdBetween(Long start, Long end);
    List<Project> findByNameContains(String pattern);
}
