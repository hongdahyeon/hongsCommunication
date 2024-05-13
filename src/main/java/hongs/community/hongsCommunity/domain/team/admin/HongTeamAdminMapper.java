package hongs.community.hongsCommunity.domain.team.admin;

import hongs.community.hongsCommunity.domain.team.admin.dto.HongTeamAdminChangeDto;
import hongs.community.hongsCommunity.domain.team.admin.vo.HongTeamAdminListVo;
import hongs.community.hongsCommunity.domain.team.admin.vo.HongTeamUserAdminListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongTeamAdminMapper {

    List<HongTeamAdminListVo> list();

    Integer changeApproval(HongTeamAdminChangeDto dto);

    Integer changeUse(HongTeamAdminChangeDto dto);

    List<HongTeamUserAdminListVo> teamUserList(Long hongTeamUid);
}
