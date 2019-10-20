package com.david.ppmtool.repositories;

import com.david.ppmtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author David Mojicevic on 20/10/2019.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}
