package hongs.community.hongsCommunity.domain.user.front.service;

import hongs.community.hongsCommunity.domain.user.front.HongUserFrontMapper;
import hongs.community.hongsCommunity.domain.user.front.dto.HongSocialUserInsertDto;
import hongs.community.hongsCommunity.domain.user.front.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
* @fileName HongSocialUserFrontService
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  소셜 로그인 서비스
**/

@Service
@RequiredArgsConstructor
public class HongSocialUserFrontService {

    private final HongUserFrontMapper userFrontMapper;

    public HongLoginUserVo findSocialUser(String userId) {
        return userFrontMapper.findSocialUser(userId);
    }

    @Transactional(readOnly = false)
    public HongLoginUserVo joinSocialUser(HongSocialUserInsertDto dto) {

        userFrontMapper.joinSocialUser(dto);

        String encode = new BCryptPasswordEncoder().encode(UUID.randomUUID().toString());

        HongUserInsertDto insertDto = new HongUserInsertDto(dto, encode);
        userFrontMapper.joinUser(insertDto);

        return userFrontMapper.findUserByUid(insertDto.getUserUid());
    }

    public Boolean findUserEmail(String userEmail) {
        Integer chkedUserEmail = userFrontMapper.chkUserEmail(userEmail);
        if(chkedUserEmail == 0) return true;
        else return false;
    }
}
