package com.example.orangeNote.project.mapper;

import com.example.orangeNote.project.domain.ProjectDomain;
import com.example.orangeNote.project.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto domainToDto(ProjectDomain projectDomain);

    ProjectDomain dtoToDomain(ProjectDto projectDto);


}
