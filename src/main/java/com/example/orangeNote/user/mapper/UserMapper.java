package com.example.orangeNote.user.mapper;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto domainToDto(UserDomain userDomain);
    @Mapping(target = "userPassword", ignore=true)
    UserDto domainToDtoSafe(UserDomain userDomain);

    UserDomain dtoToDomain(UserDto userDto);

    // 추후 매핑에서 userRole 알아서 처리하기

}
