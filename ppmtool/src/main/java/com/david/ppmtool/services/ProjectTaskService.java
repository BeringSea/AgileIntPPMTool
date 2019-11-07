package com.david.ppmtool.services;

import com.david.ppmtool.domain.Backlog;
import com.david.ppmtool.domain.Project;
import com.david.ppmtool.domain.ProjectTask;
import com.david.ppmtool.exceptions.ProjectNotFoundException;
import com.david.ppmtool.repositories.BacklogRepository;
import com.david.ppmtool.repositories.ProjectRepository;
import com.david.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author David Mojicevic on 05/11/2019.
 */
@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        try {
            // all project tasks to a specific project, not null project, that means that Backlog exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            // set the backlog to the project task
            projectTask.setBacklog(backlog);

            // set project sequence to bi nice to read
            Integer backlogSequence = backlog.getPTSequence();

            // update Backlog sequence
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);

            // add sequence to Project Task
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            // set initial status when status is null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            // set initial priority when priority is null
            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            return projectTaskRepository.save(projectTask);
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not found");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        Project project = projectRepository.findByProjectIdentifier(id);
        if(project == null){
            throw new ProjectNotFoundException("Project with ID: '" + id + "' does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id){

        // make sure we are searching on the right backlog

        return projectTaskRepository.findByProjectSequence(pt_id);
    }
}
