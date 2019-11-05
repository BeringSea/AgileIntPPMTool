package com.david.ppmtool.repositories;

import com.david.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author David Mojicevic on 05/11/2019.
 */
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {
}
