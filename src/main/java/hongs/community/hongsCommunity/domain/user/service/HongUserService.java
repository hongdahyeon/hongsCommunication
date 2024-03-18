package hongs.community.hongsCommunity.domain.user.service;

import hongs.community.hongsCommunity.domain.user.HongUserMapper;
import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HongUserService {

    private final HongUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public HongLoginUserVo findUser(String userId) {
        return userMapper.findUser(userId);
    }

    @Transactional(readOnly = false)
    public Integer join(HongUserInsertDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.joinUser(dto);
    }
}
