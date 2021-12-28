package com.ipl.dashboard;

import com.ipl.dashboard.config.AstraSecureBundleProperties;
import com.ipl.dashboard.converter.ModelConverter;
import com.ipl.dashboard.input.InputFileFieldName;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.repository.LosingMatchByTeamAndYearRepository;
import com.ipl.dashboard.repository.MatchRepository;
import com.ipl.dashboard.repository.TeamRepository;
import com.ipl.dashboard.repository.WinningMatchByTeamAndYearRepository;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@SpringBootApplication
public class IplDataLoaderService implements CommandLineRunner {

    @Value("${input.file}")
    private String fileName;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    WinningMatchByTeamAndYearRepository winningMatchByTeamAndYearRepository;

    @Autowired
    LosingMatchByTeamAndYearRepository losingMatchByTeamAndYearRepository;

    @Autowired
    TeamRepository teamRepository;

    public static void main(String[] args) {
        SpringApplication.run(IplDataLoaderService.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Parsing the {} to save Team and Match data", fileName);
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
        try(CSVReader reader = new CSVReaderBuilder(
                new FileReader(Objects.requireNonNull(this.getClass().getResource("/" + fileName)).getPath()))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()) {
            List<String[]> matches = reader.readAll();
            matches.forEach(this::intiMatchData);
            Map<String, Team> teams = new HashMap<>();
            matches.forEach(match -> this.constructTeamData(teams, match));
            teams.values().forEach( team -> {
                log.info("Saving Team data for {}", team.getName());
                teamRepository.save(team);
            });
        }
    }

    private void constructTeamData(Map<String, Team> teams, String[] inputMatch) {
        String team1 = getTeam1(inputMatch);
        String team2 = getTeam2(inputMatch);
        addWinner(teams, team1);
        addLoser(teams, team2);
    }

    private void addWinner(Map<String, Team> teams, String teamName) {
        if(teams.containsKey(teamName)) {
            Team team = teams.get(teamName);
            team.setTotalWins(team.getTotalWins() + 1);
            team.setTotalMatches(team.getTotalMatches() + 1);
        } else {
            teams.put(teamName, Team.builder()
                            .name(teamName)
                            .totalMatches(1)
                            .totalWins(1).build());
        }
    }

    private void addLoser(Map<String, Team> teams, String teamName) {
        if(teams.containsKey(teamName)) {
            Team team = teams.get(teamName);
            team.setTotalMatches(team.getTotalMatches() + 1);
        } else {
            teams.put(teamName, Team.builder()
                    .name(teamName)
                    .totalMatches(1).build());
        }
    }

    private void intiMatchData(String[] inputMatch) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String matchDataStr = inputMatch[InputFileFieldName.date.ordinal()];
        Match match = Match.builder()
                .team1(getTeam1(inputMatch))
                .id(inputMatch[InputFileFieldName.id.ordinal()])
                .year(LocalDate.parse(matchDataStr, formatter).getYear())
                .team2(getTeam2(inputMatch))
                .date(LocalDate.parse(matchDataStr, formatter))
                .winner(inputMatch[InputFileFieldName.winner.ordinal()])
                .tossDecision(inputMatch[InputFileFieldName.toss_decision.ordinal()])
                .tossWinner(inputMatch[InputFileFieldName.toss_winner.ordinal()])
                .result(getResult(inputMatch))
                .venue(inputMatch[InputFileFieldName.venue.ordinal()])
                .umpire1(inputMatch[InputFileFieldName.umpire1.ordinal()])
                .umpire2(inputMatch[InputFileFieldName.umpire2.ordinal()])
                .build();

        log.info("Saving Match for {} -> {}", inputMatch[InputFileFieldName.id.ordinal()], match);
        matchRepository.save(match);
        winningMatchByTeamAndYearRepository.save(ModelConverter.convertToWinningMatch(match));
        losingMatchByTeamAndYearRepository.save(ModelConverter.convertToLosingMatch(match));
    }

    private String getTeam1(String[] inputMatch) {
        String winner = inputMatch[InputFileFieldName.winner.ordinal()];
        String teamA = inputMatch[InputFileFieldName.team1.ordinal()];
        String teamB = inputMatch[InputFileFieldName.team2.ordinal()];
        return Objects.equals(winner, teamA) ? teamA : teamB;
    }

    private String getTeam2(String[] inputMatch) {
        String winner = inputMatch[InputFileFieldName.winner.ordinal()];
        String teamA = inputMatch[InputFileFieldName.team1.ordinal()];
        String teamB = inputMatch[InputFileFieldName.team2.ordinal()];
        return !Objects.equals(winner, teamA) ? teamA : teamB;
    }

    private String getResult(String[] inputMatch) {
        return getTeam1(inputMatch) + " wins against " + getTeam2(inputMatch)
                + " with " + inputMatch[InputFileFieldName.result_margin.ordinal()] + " "
                + inputMatch[InputFileFieldName.result.ordinal()];

    }
}
