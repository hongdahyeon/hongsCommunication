package hongs.community.hongsCommunity.domain.team.admin;

import hongs.community.hongsCommunity.domain.team.admin.dto.HongTeamAdminChangeDto;
import hongs.community.hongsCommunity.domain.team.admin.vo.HongTeamAdminListVo;
import hongs.community.hongsCommunity.domain.team.admin.vo.HongTeamUserAdminListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongTeamAdminService {

    private final HongTeamAdminMapper teamAdminMapper;

    public List<HongTeamAdminListVo> list() {
        return teamAdminMapper.list();
    }

    public Integer changeApproval(HongTeamAdminChangeDto dto) {
        return teamAdminMapper.changeApproval(dto);
    }

    public Integer changeUse(HongTeamAdminChangeDto dto) {
        return teamAdminMapper.changeUse(dto);
    }

    public List<HongTeamUserAdminListVo> teamUserList(Long hongTeamUid){
        return teamAdminMapper.teamUserList(hongTeamUid);
    }
}
