package com.work.management.service.team;

import com.work.management.dto.TeamDto;

public interface TeamService {

  void save(TeamDto document);

  TeamDto getTeamInfoByName(String name);
}
