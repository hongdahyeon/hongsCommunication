package hongs.community.hongsCommunity.domain.user.service;


import hongs.community.hongsCommunity.domain.user.HongUserMapper;
import hongs.community.hongsCommunity.domain.user.dto.HongSocialUserInsertDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
* @fileName HongSocialUserService
* @author dahyeon
* @version 1.0.0
* @date 2024-04-24
* @summary  소셜 로그인을 위한 서비스 : 소셜 로그인 찾기, 소셜 로그인 회원가입
**/

@Service
@RequiredArgsConstructor
@Slf4j
public class HongSocialUserService {

    private final HongUserMapper userMapper;

    public HongLoginUserVo findSocialUser(String userId) {
        return userMapper.findSocialUser(userId);
    }

    public Boolean findUserEmail(String userEmail) {
        Integer chkedUserEmail = userMapper.chkUserEmail(userEmail);
        if(chkedUserEmail == 0) return true;
        else return false;
    }

    @Transactional(readOnly = false)
    public HongLoginUserVo joinSocialUser(HongSocialUserInsertDto dto) {

        userMapper.joinSocialUser(dto);

        String encode = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());

        HongUserInsertDto insertDto = new HongUserInsertDto(dto, encode);
        userMapper.joinUser(insertDto);

        return userMapper.findUserByUid(insertDto.getUserUid());
    }
}
