package com.ipl.dashboard.model.Representation.assembler;

import com.ipl.dashboard.constant.Constants;
import com.ipl.dashboard.controller.MatchController;
import com.ipl.dashboard.model.Match;
import com.ipl.dashboard.model.Representation.MatchRepresentationModel;
import com.ipl.dashboard.model.converter.ModelRepresentationConverter;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MatchRepresentationModelAssembler
        extends RepresentationModelAssemblerSupport<Match, MatchRepresentationModel> {

    public MatchRepresentationModelAssembler() {
        super(MatchController.class, MatchRepresentationModel.class);
    }

    @Override
    public MatchRepresentationModel toModel(Match match) {
        MatchRepresentationModel matchRepresentationModel = ModelRepresentationConverter.convertToMatchRepresentationModel(match);
        matchRepresentationModel.add(
                linkTo(methodOn(MatchController.class).getMatchById(match.getId())).withSelfRel()
        );
        matchRepresentationModel.add(
                linkTo(methodOn(MatchController.class).getMatches(match.getTeam1(), match.getYear())).withRel(Constants.MATCHES)
        );
        return matchRepresentationModel;
    }

    public List<MatchRepresentationModel> toModels(List<Match> matches) {
        return matches.stream().map(this::toModel).collect(Collectors.toList());

    }
}
