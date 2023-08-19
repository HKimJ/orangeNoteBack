package com.example.orangeNote.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueDto {
    public Long issueId;
    public String issueName;
    public String issueContent;
    public String issueProgress;

}
