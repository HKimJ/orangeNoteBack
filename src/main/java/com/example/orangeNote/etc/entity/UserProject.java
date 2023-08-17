package com.example.orangeNote.etc.entity;


import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orange_User_Project")
@NoArgsConstructor
@AllArgsConstructor
public class UserProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDomain user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectDomain project;

}
