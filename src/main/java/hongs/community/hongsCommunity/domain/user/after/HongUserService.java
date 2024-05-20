package hongs.community.hongsCommunity.domain.user.after;

import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUnlockDto;
import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUpdteDto;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserCheckEmailVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserInfoVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserListVo;
import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import hongs.community.hongsCommunity.global.auth.PrincipalDetails;
import hongs.community.hongsCommunity.global.hongs.file.common.HongCommonFileService;
import hongs.community.hongsCommunity.global.hongs.mail.EmailService;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HongUserService {

    private final HongUserMapper hongUserMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final HongCommonFileService hongCommonFileService;

    public HongUserInfoVo userInfo(Long userUid) {
        return hongUserMapper.userInfo(userUid);
    }

    public Integer chkUserEmail(HongUserCheckEmailVo checkEmailVo) {
        return hongUserMapper.chkUserEmail(checkEmailVo);
    }

    @Transactional(readOnly = false)
    public void updateUser(HongUserUpdteDto dto) {
        if(dto.getPassword() != null) dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getProfile(), dto.getAddFile(), dto.getDelFile());
        dto.setProfile(fUid);
        hongUserMapper.updateUser(dto);

        /* 세션 정보 변경 */
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(UserUtil.isAuthenticated(authentication)) {
            PrincipalDetails details = (PrincipalDetails) authentication.getPrincipal();
            HongLoginUserVo loginUser = details.getUser();
            String profileUrl = this.getProfileUrl(dto.getUserUid());
            if(profileUrl != null) loginUser.setProfileUrl(profileUrl);
            if(dto.getUserEmail() != null) loginUser.setUserEmail(dto.getUserEmail());
            if(dto.getUserNm() != null) loginUser.setUserNm(dto.getUserNm());
        }
    }

    public String getProfileUrl(Long userUid) {
        return hongUserMapper.getProfileUrl(userUid);
    }

    public List<HongUserListVo> list() {
        return hongUserMapper.list();
    }

    public Integer unlock(HongUserUnlockDto dto) {
        return hongUserMapper.unlock(dto);
    }

    public Integer toEnable(Long userUid) {
        return hongUserMapper.toEnable(userUid);
    }

    public Integer toDisable(Long userUid) {
        return hongUserMapper.toDisable(userUid);
    }

    public void sendExpiredEmail(Long userUid) {
        String userEmail = hongUserMapper.getUserEmailByUid(userUid);
        emailService.sendExpiredEmail(userEmail);
    }

    public void sendCredentialExpiredEmail(Long userUid) {
        String userEmail = hongUserMapper.getUserEmailByUid(userUid);
        emailService.sendCredentialExpiredEmail(userEmail);
    }
}
