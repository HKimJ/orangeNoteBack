package com.example.orangeNote.user.domain;

import com.example.orangeNote.project.domain.ProjectDomain;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private String userId; // 로그인 시 아이디 겸 닉네임
    @Column(columnDefinition = "VARCHAR(30)",unique = true, updatable = false)
    private String userEmail;
    @Column(columnDefinition = "VARCHAR(20)")
    private String userPassword; // 추후 security 처리할때 다시보기
    @Temporal(value = TemporalType.DATE) @Column(columnDefinition = "DATE DEFAULT (CURRENT_DATE)", insertable = false, updatable = false)
    private Date joinDate;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'USER'", insertable = false) // 현재 권한 변경 가능한 상태
    private String userRole;

    @ManyToMany// 참여한 프로젝트, 프로젝트 entity와 매핑 예정, 데이터 타입 추후 변경
    private List<ProjectDomain> projects = new ArrayList<>();


}
