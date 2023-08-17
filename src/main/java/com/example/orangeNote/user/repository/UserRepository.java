package com.example.orangeNote.user.repository;

import com.example.orangeNote.user.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {
    UserDomain findUserByUserSeq(Long userSeq);
    UserDomain findUserByUserId(String userName);
    UserDomain findUserByUserEmail(String email);
    UserDomain save(UserDomain userDomain);
    UserDomain findUserByUserPassword(String userPassword);
}
