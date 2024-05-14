package hongs.community.hongsCommunity.domain.team.team;

import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamInsertDto;
import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamUserInsertDto;
import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamViewDto;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamListVo;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamViewVo;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HongTeamService {

    private final HongTeamMapper teamMapper;

    public List<HongTeamListVo> list() {
        Long userUid = UserUtil.getLoginUser().getUserUid();
        return teamMapper.list(userUid);
    }

    @Transactional(readOnly = false)
    public Integer insertTeam(HongTeamInsertDto dto) {
        Long representId = UserUtil.getLoginUser().getUserUid();
        dto.setRepresentId(representId);
        Integer insertTeam = teamMapper.insertTeam(dto);
        Integer insertTeamUser = this.insertTeamUser(new HongTeamUserInsertDto(representId, dto.getHongTeamUid(), "Y"));
        return (insertTeam + insertTeamUser);
    }

    @Transactional(readOnly = false)
    public Integer insertTeamUser(HongTeamUserInsertDto dto) {
        return teamMapper.insertTeamUser(dto);
    }

    public HongTeamViewVo view(Long hongTeamUid){
        return teamMapper.view(new HongTeamViewDto(hongTeamUid, UserUtil.getLoginUser().getUserUid()));
    }
}
