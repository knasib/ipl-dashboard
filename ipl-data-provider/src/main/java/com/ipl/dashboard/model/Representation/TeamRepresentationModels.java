package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class TeamRepresentationModels {
    Iterable<TeamRepresentationModel> teamRepresentationModels = new ArrayList<>();
}
