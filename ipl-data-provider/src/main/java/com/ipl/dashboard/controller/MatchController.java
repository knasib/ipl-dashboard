package com.ipl.dashboard.controller;

import com.ipl.dashboard.model.Representation.MatchRepresentationModel;
import com.ipl.dashboard.service.MatchService;
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
@RequestMapping(value = "/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @Operation(summary = "Get Match by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response.", content = {
                    @Content(mediaType = "application/match.api.v1+json",
                            schema = @Schema(implementation = MatchRepresentationModel.class)
                    )
            }),
            @ApiResponse(responseCode = "400", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping(value = "{id}")
    public @ResponseBody
    ResponseEntity<MatchRepresentationModel> getMatchById(@PathVariable @NotEmpty String id) {
        return ResponseEntity.ok(matchService.getMatch(id));
    }
}
