package com.david.ppmtool.services;

import com.david.ppmtool.domain.Project;
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

        // Logic

        return projectRepository.save(project);
    }
}
