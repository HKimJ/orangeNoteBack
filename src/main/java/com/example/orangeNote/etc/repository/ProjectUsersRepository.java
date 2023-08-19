package com.example.orangeNote.etc.repository;

import com.example.orangeNote.etc.domain.ProjectUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectUsersRepository extends JpaRepository<ProjectUsers, Long> {
    ProjectUsers save(ProjectUsers projectUsers);

}
