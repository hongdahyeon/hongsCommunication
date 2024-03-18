package hongs.community.hongsCommunity.domain.user.service;

import hongs.community.hongsCommunity.domain.user.HongUserMapper;
import hongs.community.hongsCommunity.domain.user.dto.HongUserUpdateFailCntDto;
import hongs.community.hongsCommunity.domain.user.vo.HongCheckUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HongLoginUserService {

    private final HongUserMapper userMapper;

    public HongCheckUserVo checkUser(String userId) {
        return userMapper.checkUser(userId);
    }

    public void updateFailCnt(String userId, Integer failCnt) {
        userMapper.updatePwdFailCnt(new HongUserUpdateFailCntDto(userId, failCnt + 1));
    }

    public void resetFailCnt(String userId) {
        userMapper.resetFailCnt(userId);
    }
}
