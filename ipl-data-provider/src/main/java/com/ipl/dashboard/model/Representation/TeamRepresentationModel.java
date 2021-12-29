package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class TeamRepresentationModel extends RepresentationModel<TeamRepresentationModel> {
    private String name;
    private long totalWins;
    private long totalMatches;
}
