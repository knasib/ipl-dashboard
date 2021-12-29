package com.ipl.dashboard.model.Representation.assembler;

import com.ipl.dashboard.controller.MatchController;
import com.ipl.dashboard.controller.TeamController;
import com.ipl.dashboard.model.Representation.TeamRepresentationModel;
import com.ipl.dashboard.model.Team;
import com.ipl.dashboard.model.converter.ModelRepresentationConverter;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeamRepresentationModelAssembler
        extends RepresentationModelAssemblerSupport<Team, TeamRepresentationModel> {

    public TeamRepresentationModelAssembler() {
        super(TeamController.class, TeamRepresentationModel.class);
    }

    @Override
    public TeamRepresentationModel toModel(Team team) {
        TeamRepresentationModel teamRepresentationModel = ModelRepresentationConverter.convertToTeamRepresentationModel(team);
        teamRepresentationModel.add(
                linkTo(methodOn(TeamController.class).getTeams()).withSelfRel()
        );
        teamRepresentationModel.add(
                linkTo(MatchController.class).slash(team.getName()).slash("years").slash(2020).withRel("matches")
        );
        return teamRepresentationModel;
    }
}
