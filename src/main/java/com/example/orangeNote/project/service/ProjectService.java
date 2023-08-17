package com.example.orangeNote.project.service;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.dto.ProjectDto;
import com.example.orangeNote.project.mapper.ProjectMapper;
import com.example.orangeNote.project.repository.ProjectRepository;
import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepo;
    private ProjectDomain projectDomain;
    private ProjectDto projectDto;
    private final ProjectMapper projectMapper = ProjectMapper.INSTANCE;
    private UserRepository userRepo;

    public void putData(List<ProjectDomain> sample) {
        projectRepo.saveAll(sample);
    }

    public Map<String, Object> createProject(Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>();
        try {
            String name = String.valueOf(input.get("projectName"));
//            Object member = input.get("projectMember");
            String description = String.valueOf("projectDesc");
            UserDomain creator = userRepo.findUserByUserId(String.valueOf(input.get("creator")));
            ProjectDomain project = new ProjectDomain();

            projectDto.setProjectName(name);
            projectDto.setProjectDesc(description);
            projectDto.setCreator(creator);
//            projectDto.setProjectMembers(member);

            ProjectDomain rs = projectRepo.save(projectMapper.dtoToDomain(projectDto));
            if (rs != null) {
                response.put("success", true);
                response.put("data", "프로젝트 생성에 성공했습니다");
            } else {
                response.put("success", false);
                response.put("data", "프로젝트 생성에 실패했습니다");
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("errorMessage", "프로젝트를 생성하는 동안 문제가 발생했습니다. 관리자에게 문의하세요");
            return response;
        }

    }

}
