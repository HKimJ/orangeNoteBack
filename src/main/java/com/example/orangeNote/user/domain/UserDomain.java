package com.example.orangeNote.user.domain;

import com.example.orangeNote.etc.entity.UserProject;
import com.example.orangeNote.project.domain.IssueDomain;
import com.example.orangeNote.project.domain.ProjectDomain;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@Table(name="orange_User")
@NoArgsConstructor
@AllArgsConstructor
public class UserDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userSeq; // 회원가입 번호, pk

    @Column(columnDefinition = "VARCHAR(20)",unique = true, updatable = false)
    private String userId; // 로그인 시 아이디

//    @Column(columnDefinition = "VARCHAR(20)", unique = true)
//    private String userNickName; // 닉네임(프론트 표시용...)

    @Column(columnDefinition = "VARCHAR(30)",unique = true, updatable = false)
    private String userEmail;

    @Column(columnDefinition = "VARCHAR(20)")
    private String userPassword; // 추후 security 처리할때 다시보기

    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date joinDate; // 가입일

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'USER'", insertable = false) // 현재 권한 변경 가능한 상태(updatable=true), 시큐리티 적용시 enum 고려
    private String userRole; // 권한

    @OneToMany(mappedBy = "creator")
    private Set<ProjectDomain> createdProjects = new HashSet<>(); // 본인이 생성한 프로젝트 목록

    @ManyToMany(mappedBy = "members")
    private Set<ProjectDomain> userProjects = new HashSet<>(); // 본인이 참여한 프로젝트 목록

    @OneToMany(mappedBy = "creator")
    private Set<IssueDomain> createdIssues = new HashSet<>(); // 본인이 생성한 이슈 목록

    @ManyToMany(mappedBy = "assignees")
    private Set<IssueDomain> assignedIssues = new HashSet<>(); // 본인에게 부여된 이슈 목록




}
