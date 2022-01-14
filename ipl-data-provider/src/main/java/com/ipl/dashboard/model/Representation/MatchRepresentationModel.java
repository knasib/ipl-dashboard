package com.ipl.dashboard.model.Representation;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class MatchRepresentationModel extends RepresentationModel<MatchRepresentationModel> {
    private String id;
    private String team1;
    private String team2;
    private int year;
    private LocalDate date;
    private String venue;
    private String tossWinner;
    private String tossDecision;
    private String winner;
    private String result;
    private String playerOfMatch;
    private String umpire1;
    private String umpire2;
}
