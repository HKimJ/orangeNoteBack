package com.example.orangeNote.user.repository;

import com.example.orangeNote.user.domain.UserDto;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDto, Long> {
}
