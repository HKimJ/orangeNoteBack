package com.example.orangeNote.project.domain;

import com.example.orangeNote.etc.domain.UserIssues;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import com.example.orangeNote.project.domain.IssueProgress;

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
    private String issueContent; // 이슈 내용

    @Column(name = "issue_Progress", columnDefinition = "VARCHAR(10) DEFAULT 'PRE_START'", insertable = false)
    @Enumerated(EnumType.STRING)
    private IssueProgress issueProgress; // 이슈 진행상황, enum

    @ManyToOne
    @JoinColumn(name = "creator")
    private UserDomain creator; // 이슈 생성자

    @ManyToOne
    @JoinColumn(name = "parent_Project")
    private ProjectDomain parentProject; // 이슈가 생성된 프로젝트

    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private Set<UserIssues> assignees = new HashSet<>(); // 이슈를 할당받은 사람들

    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date createdDate;

}
