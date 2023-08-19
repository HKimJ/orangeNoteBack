package com.example.orangeNote.project.domain;
import com.example.orangeNote.etc.domain.ProjectIssues;
import com.example.orangeNote.etc.domain.ProjectUsers;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name = "orange_Project")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDomain {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long projectId;

    @Column(columnDefinition = "VARCHAR(20)", unique = true)
    private String projectName; // 프로젝트 이름

    @Column(columnDefinition = "VARCHAR(255)")
    private String projectDescription; // 프로젝트 설명

    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date createdDate; // 프로젝트 생성일

    @ManyToOne
    @JoinColumn(name = "creator")
    private UserDomain creator; // 프로젝트 생성자 >> 권한 등과 연계

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<ProjectUsers> users = new HashSet<>(); // 프로젝트의 소속 멤버 목록

    @OneToMany(mappedBy = "parentProject", cascade = CascadeType.ALL)
    private List<ProjectIssues> projectIssues = new ArrayList<>(); // 프로젝트의 이슈 목록(중복 허용)


}
