package com.work.management.service.team.impl;

import com.work.management.dto.TeamDto;
import com.work.management.entity.Team;
import com.work.management.repository.TeamRepository;
import com.work.management.service.team.TeamService;
import com.work.management.utils.ExceptionUtils;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
class TeamServiceImpl implements TeamService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);
  private final TeamRepository teamRepository;

  @Autowired
  TeamServiceImpl(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public void save(TeamDto teamDto) {
    if (teamRepository.findByName(teamDto.getName()).isPresent()) {
      ExceptionUtils.throwEntityAlreadyExistsException(
          "Team already exists with this name,choose a different name");
      return;
    }

    Team team = new Team();
    BeanUtils.copyProperties(teamDto, team);

    teamRepository.save(team);
  }

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public TeamDto getTeamInfoByName(String name) {
    Optional<Team> teamInfo = teamRepository.findByName(name);
    if (!teamInfo.isPresent()) {
      ExceptionUtils.throwEntityNotFoundException(
          String.format("Team with name %s doesn't exists.", name));
    }

    TeamDto teamDto = new TeamDto();
    BeanUtils.copyProperties(teamInfo.get(), teamDto);
    return teamDto;
  }

  @Override
  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public TeamDto updateTeamEntity(TeamDto teamDto) {
    Optional<Team> teamEntity = teamRepository.findById(teamDto.getId());
    if (!teamEntity.isPresent()) {
      ExceptionUtils.throwEntityNotFoundException(
          String.format("Team with id %s doesn't exists.", teamDto.getId()));
    }

    Team oldTeamEntity = teamEntity.get();
    if (!Objects.isNull(teamDto.getName()) && !teamDto.getName().equals(oldTeamEntity.getName()) ||
        !Objects.isNull(teamDto.getId()) && !teamDto.getId().equals(oldTeamEntity.getId())) {
      ExceptionUtils.throwBadRequestException("Cannot update id or name");
    }

    Team newTeam = new Team();
    BeanUtils.copyProperties(teamDto, newTeam);

    teamRepository.save(newTeam);

    TeamDto newTeamDto = new TeamDto();
    BeanUtils.copyProperties(newTeam, newTeamDto);
    return newTeamDto;
  }
}
