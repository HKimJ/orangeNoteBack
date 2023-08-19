package com.example.orangeNote.etc.domain;

import com.example.orangeNote.project.domain.IssueDomain;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orange_User_Issues")
@NoArgsConstructor
@AllArgsConstructor
public class UserIssues {
// 유저가 보유한 issue들의 목록을 위한 중간 엔티티
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private IssueDomain issue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDomain assignee;
}
