package hongs.community.hongsCommunity.domain.user.after;

import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUpdteDto;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserCheckEmailVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class HongUserService {

    private final HongUserMapper hongUserMapper;
    private final PasswordEncoder passwordEncoder;

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
}
