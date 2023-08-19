package com.example.orangeNote.user.repository;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {
    UserDomain findUserByUserSeq(Long userSeq);
    UserDomain findUserByUserId(String userId);
    UserDomain findUserByUserEmail(String email);
    UserDomain save(UserDomain userDomain);

    Set<ProjectDomain> findByCreatedProjects(UserDomain user);
    UserDomain findUserByUserPassword(String userPassword);
}
