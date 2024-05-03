package hongs.community.hongsCommunity.domain.user.front.service;

import hongs.community.hongsCommunity.domain.user.front.HongUserFrontMapper;
import hongs.community.hongsCommunity.domain.user.front.dto.HongUserUpdateFailCntDto;
import hongs.community.hongsCommunity.domain.user.front.vo.HongCheckUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @fileName HongFrontLoginUserService
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  로그인 이전 시점 서비스 (handler쪽에서 주로 이용)
**/

@RequiredArgsConstructor
@Service
public class HongFrontLoginUserService {

    private final HongUserFrontMapper userFrontMapper;

    public HongCheckUserVo checkUser(String userId) {
        return userFrontMapper.checkUser(userId);
    }

    public void updateFailCnt(String userId, Integer failCnt) {
        userFrontMapper.updatePwdFailCnt(new HongUserUpdateFailCntDto(userId, failCnt));
    }

    public void resetFailCnt(String userId) {
        userFrontMapper.resetFailCnt(userId);
    }
}
