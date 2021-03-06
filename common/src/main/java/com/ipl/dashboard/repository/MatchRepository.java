package com.ipl.dashboard.repository;

import com.ipl.dashboard.model.Match;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CassandraRepository<Match, String> {
}
