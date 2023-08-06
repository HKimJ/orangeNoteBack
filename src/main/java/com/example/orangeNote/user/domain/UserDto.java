package com.example.orangeNote.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "User")
public class UserDto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; // 회원가입 번호
    private String user_id;
    private String email;
    private String password; // 추후 security 처리할때 다시보기
    @Temporal(value = TemporalType.DATE)
    private Date joinDate;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'USER'", insertable = false, updatable = false)
    private String privilege;
}
