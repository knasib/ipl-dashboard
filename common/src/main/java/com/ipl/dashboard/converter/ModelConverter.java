package com.ipl.dashboard.converter;

import com.ipl.dashboard.model.LosingMatchByTeamAndYear;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.WinningMatchByTeamAndYear;


public class ModelConverter {

    public static WinningMatchByTeamAndYear convertToWinningMatch(Match source) {
        return WinningMatchByTeamAndYear.builder()
                .team1(source.getTeam1())
                .id(source.getId())
                .year(source.getYear())
                .team2(source.getTeam2())
                .date(source.getDate())
                .winner(source.getWinner())
                .tossDecision(source.getTossDecision())
                .tossWinner(source.getTossWinner())
                .result(source.getResult()) //
                .venue(source.getVenue())
                .umpire1(source.getUmpire1())
                .umpire2(source.getUmpire2())
                .build();
    }

    public static LosingMatchByTeamAndYear convertToLosingMatch(Match source) {
        return LosingMatchByTeamAndYear.builder()
                .team1(source.getTeam1())
                .id(source.getId())
                .year(source.getYear())
                .team2(source.getTeam2())
                .date(source.getDate())
                .winner(source.getWinner())
                .tossDecision(source.getTossDecision())
                .tossWinner(source.getTossWinner())
                .result(source.getResult()) //
                .venue(source.getVenue())
                .umpire1(source.getUmpire1())
                .umpire2(source.getUmpire2())
                .build();
    }

    public static Match convertToMatch(WinningMatchByTeamAndYear source) {
        return Match.builder()
                .team1(source.getTeam1())
                .id(source.getId())
                .year(source.getYear())
                .team2(source.getTeam2())
                .date(source.getDate())
                .winner(source.getWinner())
                .tossDecision(source.getTossDecision())
                .tossWinner(source.getTossWinner())
                .result(source.getResult()) //
                .venue(source.getVenue())
                .umpire1(source.getUmpire1())
                .umpire2(source.getUmpire2())
                .build();
    }

    public static Match convertToMatch(LosingMatchByTeamAndYear source) {
        return Match.builder()
                .team1(source.getTeam1())
                .id(source.getId())
                .year(source.getYear())
                .team2(source.getTeam2())
                .date(source.getDate())
                .winner(source.getWinner())
                .tossDecision(source.getTossDecision())
                .tossWinner(source.getTossWinner())
                .result(source.getResult()) //
                .venue(source.getVenue())
                .umpire1(source.getUmpire1())
                .umpire2(source.getUmpire2())
                .build();
    }
}
