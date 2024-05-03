package hongs.community.hongsCommunity.domain.user.front.service;

import hongs.community.hongsCommunity.domain.user.front.HongUserFrontMapper;
import hongs.community.hongsCommunity.domain.user.front.dto.*;
import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import hongs.community.hongsCommunity.global.hongs.mail.EmailService;
import hongs.community.hongsCommunity.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @fileName HongUserFrontService
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  로그인 이전 시점의 서비스
**/

@Service
@RequiredArgsConstructor
public class HongUserFrontService {

    private final HongUserFrontMapper userFrontMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public HongLoginUserVo findUser(String userId) {
        return userFrontMapper.findUser(userId);
    }

    @Transactional(readOnly = false)
    public Integer join(HongUserInsertDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userFrontMapper.joinUser(dto);
    }

    public Boolean searchId(HongSearchIdPwdDto dto){
        String searchId = userFrontMapper.searchId(dto);
        if(searchId == null) return false;
        else {
            emailService.sendSearchId(dto.getUserEmail(), searchId);
            return true;
        }
    }

    @Transactional(readOnly = false)
    public Integer changePwd(HongUserChngPwdDto dto) {
        if(dto.getChngPwd()) dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userFrontMapper.chngPwd(dto);
    }

    public Boolean searchPwd(HongSearchIdPwdDto dto){
        String searchId = userFrontMapper.searchId(dto);
        if(searchId == null) return false;
        else {
            String initialPassword = StringUtil.random(6);
            String encodePassword = passwordEncoder.encode(initialPassword);
            userFrontMapper.updateUserPwd(new HongUserUpdatePwdDto(encodePassword, dto));
            emailService.sendInitialPwdEmail(dto.getUserEmail(), initialPassword);
            return true;
        }
    }

    public Boolean sendEmail(HongUserSendEmailDto dto) {
        Integer searchedUser = userFrontMapper.searchUser(dto);
        if(searchedUser == 0) return false;
        else {
            emailService.sendVerification(dto.getUserEmail());
            return true;
        }
    }

    public Boolean chkUserId(String userId) {
        Integer chkedUserId = userFrontMapper.chkUserId(userId);
        if(chkedUserId == 0) return true;
        else return false;
    }

    public Integer chkUserEmail(String userEmail) {
        return userFrontMapper.chkUserEmail(userEmail);
    }

}
