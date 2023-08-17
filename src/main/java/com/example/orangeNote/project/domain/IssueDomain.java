package com.example.orangeNote.project.domain;

import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "orange_Issue")
@NoArgsConstructor
@AllArgsConstructor
public class IssueDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @Column(name = "issue_Name")
    private String issueName; // 이슈 이름(제목)

    @Column(name = "issue_Content")
    private String content; // 이슈 내용

    @Column(name = "issue_Progress")
    private String progress; // 이슈 진행상황, enum 고려

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectDomain parentProject; // 이슈가 생성된 프로젝트

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private UserDomain creator; // 이슈 생성자

    @ManyToMany
    @JoinTable(
            name = "issue_assignees",
            joinColumns = @JoinColumn(name = "issue_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<UserDomain> assignees = new HashSet<>(); // 이슈를 할당받은 사람들



    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date createdDate;

}
