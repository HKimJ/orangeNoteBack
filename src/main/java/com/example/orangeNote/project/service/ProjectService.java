package com.example.orangeNote.project.service;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.repository.ProjectRepository;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepo;

    public void putData(List<ProjectDomain> sample) {
        projectRepo.saveAll(sample);
    }

}
