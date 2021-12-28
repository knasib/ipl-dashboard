package com.ipl.dashboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(value = "team")
public class Team {
    @PrimaryKeyColumn(name = "team_name", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String name;

    @Column("total_wins")
    @CassandraType(type = CassandraType.Name.BIGINT)
    private long totalWins;

    @Column("total_matches")
    @CassandraType(type = CassandraType.Name.BIGINT)
    private long totalMatches;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(getName(), team.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
