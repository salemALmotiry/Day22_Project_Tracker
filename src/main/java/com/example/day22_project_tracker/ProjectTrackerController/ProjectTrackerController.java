package com.example.day22_project_tracker.ProjectTrackerController;

import com.example.day22_project_tracker.ApiResponse.ApiResponse;
import com.example.day22_project_tracker.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/projects-tracker")
public class ProjectTrackerController {

    ArrayList<Project> projects = new ArrayList<Project>();

    @GetMapping("/get")
    public ResponseEntity getProjects() {
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projects.add(project);
        return ResponseEntity.status(201).body(new ApiResponse("Project added successfully"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index,@RequestBody @Valid Project project, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index) {

        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }


    @PutMapping("chnage-status/{index}/{status}")
    public ResponseEntity changeProjectStatus(@PathVariable int index, @PathVariable String status ) {
        String regexp = "^(Not Started|in Progress|Completed)$";
        if (status.matches(regexp)){
            projects.get(index).setStatus(status);
            return ResponseEntity.status(200).body(new ApiResponse("Status updated successfully"));

        }else {
            return ResponseEntity.status(400).body(new ApiResponse("Status must be Not Started or in Progress or Completed only"));

        }


    }


    @GetMapping("/search-by-title/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){

        for(Project project : this.projects){
            if (project.getTitle().equalsIgnoreCase(title))
                return ResponseEntity.status(200).body(project);
        }

        return ResponseEntity.status(400).body("Project not found");
    }

    @GetMapping("/search-by-companyname/{companyName}")
    public ResponseEntity searchByCompanyName(@PathVariable String companyName){
        ArrayList<Project> temp = new ArrayList<>();

        for (Project project:projects){
            if (project.getCompanyName().equalsIgnoreCase(companyName)){
                temp.add(project);
            }
        }

        if (temp.isEmpty())
            return ResponseEntity.status(400).body(new ApiResponse("There is not projects under this company"));
        else
            return ResponseEntity.status(200).body(temp);
    }

}
