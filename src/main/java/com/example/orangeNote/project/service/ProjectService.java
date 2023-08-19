package com.example.orangeNote.project.service;

import com.example.orangeNote.etc.domain.ProjectUsers;
import com.example.orangeNote.etc.repository.ProjectUsersRepository;
import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.dto.ProjectDto;
import com.example.orangeNote.project.mapper.ProjectMapper;
import com.example.orangeNote.project.repository.ProjectRepository;
import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepo;

    private final ProjectUsersRepository projectUserRepo;

    private final ProjectMapper projectMapper = ProjectMapper.INSTANCE;
    private final UserRepository userRepo;

    public Map<String, Object> createProject(Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>();
        try {
            String name = String.valueOf(input.get("projectName"));
            String description = String.valueOf(input.get("projectDesc"));
            UserDomain creator = userRepo.findUserByUserId(String.valueOf(input.get("creator")));
            Set<UserDomain> members = new HashSet<>((Collection) input.get("projectMembers"));

            ProjectDto projectDto = new ProjectDto();
            projectDto.setProjectName(name);
            projectDto.setProjectDesc(description);
            projectDto.setCreator(creator);
            projectDto.setProjectMembers(members);
            ProjectDomain rs = projectRepo.save(projectMapper.dtoToDomain(projectDto));
            if (rs != null) {
                ProjectUsers projectUsers = new ProjectUsers();
                projectUsers.setUsers(creator);
                projectUsers.setProject(rs);
                projectUserRepo.save(projectUsers);
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
            response.put("data", "프로젝트를 생성하는 동안 문제가 발생했습니다. 관리자에게 문의하세요");
            return response;
        }
    }

    public Map<String, Object> addProjectMember(Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>();
        try {

        } catch (Exception e) {

        }
        return null;
    }

    public Map<String, Object> findProjectListByCreator(Map<String, Object> input) {
        Map<String, Object> response = new HashMap<>();
        try {
            UserDomain creator = userRepo.findUserByUserId(String.valueOf(input.get("userId")));
            Set<ProjectDomain> result = creator.getCreatedProjects();
            response.put("success", true);
            response.put("data", result);
            return response;
        } catch(Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("data", "사용자가 생성한 프로젝트 목록을 가져오지 못했습니다.");
            return response;
        }


    }

}
