package com.ipl.dashboard.model.converter;

import com.ipl.dashboard.converter.ModelConverter;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Representation.MatchRepresentationModel;
import com.ipl.dashboard.model.Representation.TeamRepresentationModel;
import com.ipl.dashboard.model.Team;

public class ModelRepresentationConverter extends ModelConverter {
    public static MatchRepresentationModel convertToMatchRepresentationModel(Match source) {
        return MatchRepresentationModel.builder()
                .team1(source.getTeam1())
                .id(source.getId())
                .year(source.getYear())
                .team2(source.getTeam2())
                .date(source.getDate())
                .winner(source.getWinner())
                .tossDecision(source.getTossDecision())
                .tossWinner(source.getTossWinner())
                .result(source.getResult())
                .playerOfMatch(source.getPlayerOfMatch())
                .venue(source.getVenue())
                .umpire1(source.getUmpire1())
                .umpire2(source.getUmpire2())
                .build();
    }

    public static TeamRepresentationModel convertToTeamRepresentationModel(Team team) {
        return TeamRepresentationModel.builder()
                .name(team.getName())
                .totalMatches(team.getTotalMatches())
                .totalWins(team.getTotalWins())
                .build();
    }
}
