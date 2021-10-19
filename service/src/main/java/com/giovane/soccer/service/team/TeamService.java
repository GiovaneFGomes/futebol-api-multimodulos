package com.giovane.soccer.service.team;

import java.util.List;

import com.giovane.soccer.mapper.request.TeamRequestMapper;
import com.giovane.soccer.model.entity.TeamEntity;
import com.giovane.soccer.model.request.TeamRequestService;
import com.giovane.soccer.model.response.TeamResponseService;
import com.giovane.soccer.repository.TeamRepository;
import com.giovane.soccer.exceptions.notfound.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.giovane.soccer.mapper.request.TeamRequestMapper.toTeamEntity;
import static com.giovane.soccer.mapper.response.TeamResponseMapper.toTeamServiceResponse;

@AllArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamResponseService saveTeam(TeamRequestService team) {
        TeamEntity teamEntity = toTeamEntity(team);
        TeamEntity teamSave = teamRepository.save(teamEntity);
        return toTeamServiceResponse(teamSave);
    }

    public TeamResponseService updateTeamById(TeamRequestService team, Integer id){
        TeamEntity teamId = teamRepository
                .findById(id).orElseThrow(() -> new NotFoundException("ID not found"));
        team.setId(teamId.getId());
        TeamEntity teamEntity = toTeamEntity(team);
        TeamEntity teamSave = teamRepository.save(teamEntity);
        return toTeamServiceResponse(teamSave);
    }

    public void deleteTeamById(Integer id) {
        teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID not found"));
        teamRepository.deleteById(id);
    }

    public TeamResponseService findTeamById(Integer id){
        TeamEntity teamId = teamRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID not found"));
        return toTeamServiceResponse(teamId);
    }

//    public List<TeamResponseService> findAllTeams() {
//        return teamRepository.findAll().stream()
//                .map(TeamRequestMapper.INSTANCE::toTeamResponseDto)
//                        .toList();
//    }

}
