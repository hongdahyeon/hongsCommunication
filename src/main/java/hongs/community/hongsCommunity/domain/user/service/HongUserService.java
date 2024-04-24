package hongs.community.hongsCommunity.domain.user.service;

import hongs.community.hongsCommunity.domain.user.HongUserMapper;
import hongs.community.hongsCommunity.domain.user.dto.HongSearchIdPwdDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserUpdatePwdDto;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import hongs.community.hongsCommunity.global.hongs.mail.EmailService;
import hongs.community.hongsCommunity.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @fileName HongUserService
* @author dahyeon
* @version 1.0.0
* @date 2024-04-24
* @summary  기본 유저 서비스
**/

@Service
@RequiredArgsConstructor
@Slf4j
public class HongUserService {

    private final HongUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public HongLoginUserVo findUser(String userId) {
        return userMapper.findUser(userId);
    }

    @Transactional(readOnly = false)
    public Integer join(HongUserInsertDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.joinUser(dto);
    }

    public Boolean searchId(HongSearchIdPwdDto dto){
        String searchId = userMapper.searchId(dto);
        if(searchId == null) return false;
        else {
            emailService.sendSearchId(dto.getUserEmail(), searchId);
            return true;
        }
    }

    public Boolean searchPwd(HongSearchIdPwdDto dto){
        String searchId = userMapper.searchId(dto);
        if(searchId == null) return false;
        else {
            String initialPassword = StringUtil.random(6);
            String encodePassword = passwordEncoder.encode(initialPassword);
            userMapper.updateUserPwd(new HongUserUpdatePwdDto(encodePassword, dto));
            emailService.sendInitialPwdEmail(dto.getUserEmail(), initialPassword);
            return true;
        }
    }
}
