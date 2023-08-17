package com.example.orangeNote.project.dto;

import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private String projectId;
    private String projectName;
    private UserDomain creator;
    private Set<UserDomain> projectMembers = new HashSet<>();
    private String projectDesc;
}
