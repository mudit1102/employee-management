package com.work.management.service.team.impl;

import com.work.management.dto.TeamDto;
import com.work.management.entity.Team;
import com.work.management.repository.TeamRepository;
import com.work.management.service.team.TeamService;
import com.work.management.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class TeamServiceImpl implements TeamService {

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
}
