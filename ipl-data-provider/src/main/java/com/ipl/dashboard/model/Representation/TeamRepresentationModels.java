package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TeamRepresentationModels {
    Iterable<TeamRepresentationModel> teamRepresentationModels;
}
