package hongs.community.hongsCommunity.global.auth;

import hongs.community.hongsCommunity.domain.user.HongUserService;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final HongUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        HongLoginUserVo user = userService.findUser(userId);
        if(user == null) throw new UsernameNotFoundException(userId + " 사용자가 없습니다.");
        else return new PrincipalDetails(user);
    }
}
