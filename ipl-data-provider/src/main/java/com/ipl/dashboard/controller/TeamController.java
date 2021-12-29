package com.ipl.dashboard.controller;

import com.ipl.dashboard.model.Representation.TeamRepresentationModels;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.service.TeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

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

}
