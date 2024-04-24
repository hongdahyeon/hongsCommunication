package hongs.community.hongsCommunity.domain.user.service;

import hongs.community.hongsCommunity.domain.user.HongUserMapper;
import hongs.community.hongsCommunity.domain.user.dto.HongUserUpdateFailCntDto;
import hongs.community.hongsCommunity.domain.user.vo.HongCheckUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @fileName HongLoginUserService
* @author dahyeon
* @version 1.0.0
* @date 2024-04-24
* @summary  로그인 시점에 사용되는 service : 유저 찾기, 비번실패횟수 업데이트
**/

@Service
@RequiredArgsConstructor
@Slf4j
public class HongLoginUserService {

    private final HongUserMapper userMapper;

    public HongCheckUserVo checkUser(String userId) {
        return userMapper.checkUser(userId);
    }

    public void updateFailCnt(String userId, Integer failCnt) {
        userMapper.updatePwdFailCnt(new HongUserUpdateFailCntDto(userId, failCnt));
    }

    public void resetFailCnt(String userId) {
        userMapper.resetFailCnt(userId);
    }
}
