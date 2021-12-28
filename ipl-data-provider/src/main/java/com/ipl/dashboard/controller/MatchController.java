package com.ipl.dashboard.controller;

import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "matches")
public class MatchController {
    @Autowired
    MatchService matchService;

    @Operation(summary = "Get all Matches by a team for a specific year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/match.api.v1+json",
                            schema = @Schema(implementation = Match.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(value = "{teamname}/years/{year}")
    public List<Match> getMatches(@PathVariable @NotEmpty String teamname,
                                  @PathVariable @NotEmpty Integer year) {
        return matchService.getMatches(teamname, year);
    }

    @Operation(summary = "Get Match by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/match.api.v1+json",
                            schema = @Schema(implementation = Match.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(value = "{id}")
    public Optional<Match> getMatchById(@PathVariable @NotEmpty String id) {
        return matchService.getMatch(id);
    }
}
