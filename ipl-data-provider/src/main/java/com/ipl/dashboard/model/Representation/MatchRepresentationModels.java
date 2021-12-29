package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchRepresentationModels {
    private Iterable<MatchRepresentationModel> matchRepresentationModels;
}
