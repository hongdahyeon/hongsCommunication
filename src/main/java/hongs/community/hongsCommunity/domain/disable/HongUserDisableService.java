package hongs.community.hongsCommunity.domain.disable;

import hongs.community.hongsCommunity.domain.disable.dto.HongUserDisableDto;
import hongs.community.hongsCommunity.domain.disable.dto.HongUserEnableDto;
import hongs.community.hongsCommunity.domain.disable.vo.HongUserDisableViewVo;
import hongs.community.hongsCommunity.domain.user.after.HongUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HongUserDisableService {

    private final HongUserDisableMapper disableMapper;
    private final HongUserService userService;

    public HongUserDisableViewVo view(Long userUid) {
        return disableMapper.view(userUid);
    }

    @Transactional(readOnly = false)
    public Integer toEnable(HongUserEnableDto dto){
        Integer enableUser = userService.toEnable(dto.getUserUid());
        Integer enable = disableMapper.toEnable(dto);
        return (enableUser + enable);
    }

    @Transactional(readOnly = false)
    public Integer toDisable(HongUserDisableDto dto) {
        Integer disableUser = userService.toDisable(dto.getUserUid());
        Integer disable = disableMapper.toDisable(dto);
        return (disableUser + disable);
    }

    public HongUserDisableViewVo viewMsg(String userId) {
        return disableMapper.viewMsg(userId);
    }
}
