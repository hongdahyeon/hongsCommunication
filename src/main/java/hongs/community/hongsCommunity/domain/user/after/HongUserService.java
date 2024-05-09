package hongs.community.hongsCommunity.domain.user.after;

import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUnlockDto;
import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUpdteDto;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserCheckEmailVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserInfoVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserListVo;
import hongs.community.hongsCommunity.global.hongs.mail.EmailService;
import lombok.RequiredArgsConstructor;
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

    public HongUserInfoVo userInfo(Long userUid) {
        return hongUserMapper.userInfo(userUid);
    }

    public Integer chkUserEmail(HongUserCheckEmailVo checkEmailVo) {
        return hongUserMapper.chkUserEmail(checkEmailVo);
    }

    @Transactional(readOnly = false)
    public void updateUser(HongUserUpdteDto dto) {
        if(dto.getPassword() != null) dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        hongUserMapper.updateUser(dto);
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
