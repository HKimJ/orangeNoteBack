package com.example.orangeNote.project.domain;

import com.example.orangeNote.api.JsonAttributeConverter;
import com.example.orangeNote.user.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "orange_Projects")
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDomain {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long projectId;

    @Column(columnDefinition = "VARCHAR(20)", unique = true)
    private String projectName;

    @ManyToMany(mappedBy = "projects")
    @Convert(converter = JsonAttributeConverter.class)
    @Column(columnDefinition = "JSON")
    private List<UserDomain> projectMember = new ArrayList<>();

    @Convert(converter = JsonAttributeConverter.class)
    @Column(columnDefinition = "JSON")
    private Map<String, Object> projectIssue;

    @Column(columnDefinition = "VARCHAR(50)")
    private String projectDescription;

    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date createdDate;



}
