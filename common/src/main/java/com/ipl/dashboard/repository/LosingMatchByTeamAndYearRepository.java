package com.ipl.dashboard.repository;

import com.ipl.dashboard.model.LosingMatchByTeamAndYear;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface LosingMatchByTeamAndYearRepository extends CassandraRepository<LosingMatchByTeamAndYear, String> {
    List<LosingMatchByTeamAndYear> findAllByTeam2AndYear(String team1, int year);
}
