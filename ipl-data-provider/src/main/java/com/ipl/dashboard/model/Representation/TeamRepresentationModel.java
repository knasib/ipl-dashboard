package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class TeamRepresentationModel extends RepresentationModel<TeamRepresentationModel> {
    private String name;
    private long totalWins;
    private long totalMatches;
}
