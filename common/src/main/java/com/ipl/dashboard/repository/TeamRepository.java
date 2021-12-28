package com.ipl.dashboard.repository;

import com.ipl.dashboard.model.Team;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CassandraRepository<Team, String> {
}
