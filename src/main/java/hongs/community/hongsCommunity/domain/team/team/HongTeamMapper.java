package hongs.community.hongsCommunity.domain.team.team;

import hongs.community.hongsCommunity.domain.team.team.dto.*;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamListVo;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamUserListVo;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongTeamMapper {

    List<HongTeamListVo> list(HongTeamListDto dto);

    Integer insertTeam(HongTeamInsertDto dto);

    Integer insertTeamUser(HongTeamUserInsertDto dto);

    HongTeamViewVo view(HongTeamViewDto dto);

    Integer update(HongTeamUpdateDto dto);

    List<HongTeamUserListVo> userList(Long hongTeamUid);

    Integer userApproval(HongTeamUserApprovalDto dto);
}
