package com.example.orangeNote.user.mapper;

import com.example.orangeNote.user.domain.UserDomain;
import com.example.orangeNote.user.dto.UserDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto domainToDto(UserDomain userDomain);
    @Mapping(target = "userPassword", ignore =true)
    @Mapping(target = "userRole", ignore = true)
    UserDto domainToDtoSafe(UserDomain userDomain); // 현재 동작하지 않음, 추후에 수정해서 반영

    UserDomain dtoToDomain(UserDto userDto);

//    default UserDto createUserDtoFromUserDomain(UserDomain userDomain) {
//        if (userDomain == null) {
//            return null;
//        }
//        return new UserDto(userDomain.getUserName(), userDomain.getUserEmail());
//   } 현재 동작하지 않음 추후에 수정해서 반영

}
