package com.ipl.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(value = "losing_match_by_team_and_year")
public class LosingMatchByTeamAndYear {
    @PrimaryKeyColumn(name = "team_2", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String team2;

    @PrimaryKeyColumn(name = "year", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private int year;

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String id;

    @Column("team_1")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String team1;

    @Column("match_date")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate date;

    @Column("venue")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String venue;

    @Column("toss_winner")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String tossWinner;

    @Column("toss_decision")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String tossDecision;

    @Column("winner")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String winner;

    @Column("result")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String result;

    @Column("player_of_match")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String playerOfMatch;

    @Column("umpire_1")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String umpire1;

    @Column("umpire_2")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String umpire2;
}
