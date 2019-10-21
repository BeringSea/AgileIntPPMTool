package com.david.ppmtool.services;

import com.david.ppmtool.domain.Project;
import com.david.ppmtool.exceptions.ProjectIdException;
import com.david.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author David Mojicevic on 20/10/2019.
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch (Exception e){
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null)
            throw new ProjectIdException("Project ID " + projectId + " does not exist");
        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if(project == null)
            throw new ProjectIdException("Cannot delete project with " + projectId + " ID" + "Project ID " + projectId + " does not exist");
        projectRepository.delete(project);
    }


}
