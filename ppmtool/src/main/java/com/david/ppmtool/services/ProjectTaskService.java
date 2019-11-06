package com.david.ppmtool.services;

import com.david.ppmtool.domain.Backlog;
import com.david.ppmtool.domain.ProjectTask;
import com.david.ppmtool.repositories.BacklogRepository;
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

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

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
    }

    public Iterable<ProjectTask> findBacklogById(String id) {
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }
}
