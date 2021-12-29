package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class MatchRepresentationModels {
    private Iterable<MatchRepresentationModel> matchRepresentationModels = new ArrayList<>();
}
