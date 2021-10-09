package com.giovane.soccer.contract.v2;

import com.giovane.soccer.config.swagger.annotations.standardcodes.*;
import com.giovane.soccer.dto.TeamRequestDto;
import com.giovane.soccer.dto.TeamRequestPatchDto;
import com.giovane.soccer.dto.TeamResponseDto;
import com.giovane.soccer.dto.TeamResponsePatchDto;
import com.giovane.soccer.service.team.TeamServiceFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/v2/soccer/team")
public class TeamControllerv2 {

    private final TeamServiceFacade teamService;

    @ResponseStatus(CREATED)
    @PostMapping
    @TeamSaveStandardsCode
    public TeamResponseDto saveTeam(@RequestBody @Valid TeamRequestDto team) {
        return teamService.saveTeam(team);
    }

    @ResponseStatus(NO_CONTENT)
    @PutMapping(path = "/{id}")
    @TeamPutStandardCode
    public void updateTeamById(@RequestBody @Valid TeamRequestDto team, @PathVariable("id") Integer id) {
        teamService.updateTeamById(team, id);
    }

    @ResponseStatus(NO_CONTENT)
    @PatchMapping(path = "/{id}")
    public TeamResponsePatchDto patchTeamById(@RequestBody @Valid TeamRequestPatchDto team, @PathVariable("id") Integer id) {
        return teamService.patchTeamById(team, id);
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    @TeamDeleteIdStandardCode
    public void deleteTeamById(@PathVariable("id") Integer id) {
        teamService.deleteTeamById(id);
    }

    @ResponseStatus(OK)
    @GetMapping(path = "/{id}")
    @TeamGetIdStandardCode
    public TeamResponseDto findTeamById(@PathVariable("id") Integer id) {
        return teamService.findTeamById(id);
    }

    @ResponseStatus(OK)
    @GetMapping(path = "/findAll")
    @TeamGetAllStandardCode
    public List<TeamResponseDto> findAllTeams() {
        return teamService.findAllTeams();
    }

}
