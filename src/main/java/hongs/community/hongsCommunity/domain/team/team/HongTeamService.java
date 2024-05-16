package hongs.community.hongsCommunity.domain.team.team;

import hongs.community.hongsCommunity.domain.team.team.dto.*;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamListVo;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamUserListVo;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamViewVo;
import hongs.community.hongsCommunity.global.hongs.file.common.HongCommonFileService;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HongTeamService {

    private final HongTeamMapper teamMapper;
    private final HongCommonFileService hongCommonFileService;

    public List<HongTeamListVo> list(String search) {
        return teamMapper.list(new HongTeamListDto(UserUtil.getLoginUserUid(), search));
    }

    @Transactional(readOnly = false)
    public Integer insertTeam(HongTeamInsertDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getTeamProfile(), dto.getAddFile(), dto.getDelFile());
        Long representId = UserUtil.getLoginUserUid();
        dto.setRepresent_Profile(fUid, representId);
        Integer insertTeam = teamMapper.insertTeam(dto);
        Integer insertTeamUser = this.insertTeamUser(new HongTeamUserInsertDto(representId, dto.getHongTeamUid(), "Y"));
        return (insertTeam + insertTeamUser);
    }

    @Transactional(readOnly = false)
    public Integer insertTeamUser(HongTeamUserInsertDto dto) {
        return teamMapper.insertTeamUser(dto);
    }

    public HongTeamViewVo view(Long hongTeamUid){
        return teamMapper.view(new HongTeamViewDto(hongTeamUid, UserUtil.getLoginUserUid()));
    }

    @Transactional(readOnly = false)
    public Integer update(HongTeamUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getTeamProfile(), dto.getAddFile(), dto.getDelFile());
        dto.setTeamProfile(fUid);
        return teamMapper.update(dto);
    }

    public List<HongTeamUserListVo> userList(Long hongTeamUid) {
        return teamMapper.userList(hongTeamUid);
    }

    public Integer userApproval(HongTeamUserApprovalDto dto) {
        return teamMapper.userApproval(dto);
    }
}
