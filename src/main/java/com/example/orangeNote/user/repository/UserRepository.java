package com.example.orangeNote.user.repository;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {
    UserDomain findUserByUserName(String userName);
    UserDomain findUserByUserPassword(String userPassword);
}
