package com.example.orangeNote.project.repository;

import com.example.orangeNote.project.domain.ProjectDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepository extends JpaRepository<ProjectDomain, Long> {

    ProjectDomain save(ProjectDomain projectDomain);
    ProjectDomain findProjectByProjectId(Long id);
    ProjectDomain findProjectByProjectName(String name);

}
