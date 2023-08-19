package com.example.orangeNote.etc.domain;


import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "orange_Project_Members")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUsers {
// 프로젝트에 속한 멤버들을 위한 중간 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "members_of_project")
    private UserDomain users;

    @ManyToOne
    @JoinColumn(name = "project_Id")
    private ProjectDomain project;

}
