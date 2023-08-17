package com.example.orangeNote.etc.entity;

import com.example.orangeNote.project.domain.IssueDomain;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orange_User_Issue")
@NoArgsConstructor
@AllArgsConstructor
public class UserIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id")
    private IssueDomain issue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDomain user;
}
