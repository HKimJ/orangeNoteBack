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
@Table(name = "orange_Project_Issues")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectIssues {
// 프로젝트에 속한 issue들을 위한 중간 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String issueName;

    @ManyToOne
    @JoinColumn(name = "creator")
    private UserDomain creator;

    @ManyToOne
    @JoinColumn(name= "parent_Project")
    private ProjectDomain parentProject;

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private Set<UserIssues> assignees = new HashSet<>();


}
