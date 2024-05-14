package hongs.community.hongsCommunity.domain.team.team;

import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamInsertDto;
import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamUserInsertDto;
import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamViewDto;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamListVo;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongTeamMapper {

    List<HongTeamListVo> list(Long loginUser);

    Integer insertTeam(HongTeamInsertDto dto);

    Integer insertTeamUser(HongTeamUserInsertDto dto);

    HongTeamViewVo view(HongTeamViewDto dto);
}
