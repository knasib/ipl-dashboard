package com.ipl.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
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

@Table(value = "match")
public class Match {
    @Id
    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column("team_1")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String team1;

    @Column("team_2")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String team2;

    @Column("year")
    @CassandraType(type = CassandraType.Name.INT)
    private int year;

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
