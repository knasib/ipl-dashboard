package com.ipl.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(value = "team")
public class Team {
    @Id
    @PrimaryKeyColumn(name = "team_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String name;

    @Column("total_wins")
    @CassandraType(type = CassandraType.Name.BIGINT)
    private long totalWins;

    @Column("total_matches")
    @CassandraType(type = CassandraType.Name.BIGINT)
    private long totalMatches;
}
