package com.ipl.dashboard.controller;

import com.ipl.dashboard.model.Representation.MatchRepresentationModels;
import com.ipl.dashboard.model.Representation.TeamRepresentationModels;
import com.ipl.dashboard.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@CrossOrigin
@RestController
@RequestMapping(value = "/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Operation(summary = "Get all Team.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/team.api.v1+json",
                            schema = @Schema(implementation = TeamRepresentationModels.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(value = "")
    public @ResponseBody
    ResponseEntity<TeamRepresentationModels> getTeams() {
        TeamRepresentationModels teams = teamService.getTeams();
        return ResponseEntity.ok(teams);
    }

    @Operation(summary = "Get all Matches by a team for a specific year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/match.api.v1+json",
                            schema = @Schema(implementation = MatchRepresentationModels.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(value = "{teamname}/years/{year}")
    public @ResponseBody
    ResponseEntity<MatchRepresentationModels> getMatches(@PathVariable @NotEmpty String teamname,
                                                         @PathVariable @NotEmpty Integer year) {
        return ResponseEntity.ok(teamService.getMatches(teamname, year));
    }

}
