package com.example.day22_project_tracker.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Project {

    @NotNull(message = "Enter project id")
    @Min(value = 2,message = "project id must be longer than 2")
    @Positive(message = "project id must must be positive")
    private int ID;

    @NotEmpty(message = "Enter project title")
    @Size(min = 8,message = "project title length must be more than 8 ")
    private String title;

    @NotEmpty(message = "Enter project description")
    @Size(min = 15,message = "project description length must be more than 15 ")
    private String description;

    @NotEmpty(message = "Enter project status")
    @Pattern(regexp = "^(Not Started|in Progress|Completed)$",message = "project status must be:\n" +
            "1- Not Started\n" +
            "2- in Progress\n" +
            "3- Completed only \n")
    private String status;

    @NotNull(message = "Enter company name")
    @Size(min = 6 ,message = "company name must be longer than 6")
    private String companyName;


}
