package com.example.orangeNote.project.domain;

import com.example.orangeNote.api.JsonAttributeConverter;
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
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserDomain creator; // 프로젝트 생성자 >> 권한 등과 연계

    @ManyToMany
    @JoinTable(
            name = "project_member",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserDomain> members = new HashSet<>(); // 프로젝트 참여자 목록

    @OneToMany(mappedBy = "parentProject")
    private Set<IssueDomain> projectIssues = new HashSet<>(); // 프로젝트 이슈 목록

    @Column(columnDefinition = "VARCHAR(255)")
    private String projectDescription; // 프로젝트 설명

    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date createdDate; // 프로젝트 생성일

}
