package com.david.ppmtool.repositories;

import com.david.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author David Mojicevic on 05/11/2019.
 */
@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
}
