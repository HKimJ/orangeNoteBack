package com.example.orangeNote.project.mapper;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto domainToDto(ProjectDomain projectDomain);
    @Mapping(target = "projectId", ignore =true)
    @Mapping(target = "createdDate", ignore = true)
    ProjectDomain dtoToDomain(ProjectDto projectDto);


}
