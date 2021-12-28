package com.ipl.dashboard.repository;

import com.ipl.dashboard.model.WinningMatchByTeamAndYear;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface WinningMatchByTeamAndYearRepository extends CassandraRepository<WinningMatchByTeamAndYear, String> {
    List<WinningMatchByTeamAndYear> findAllByTeam1AndYear(String team1, int year);
}
