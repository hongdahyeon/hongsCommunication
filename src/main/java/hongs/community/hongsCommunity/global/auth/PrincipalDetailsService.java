package hongs.community.hongsCommunity.global.auth;

import hongs.community.hongsCommunity.domain.menu.HongMenuService;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import hongs.community.hongsCommunity.domain.user.service.HongUserService;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final HongUserService userService;
    private final HongMenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        HongLoginUserVo user = userService.findUser(userId);

        if(user != null) {
            this.customUser(user);
            return new PrincipalDetails(user);
        } else throw new UsernameNotFoundException(userId + " 사용자가 없습니다.");
    }

    public void customUser(HongLoginUserVo user) {
        List<HongMenuVo> list = menuService.list(user.getRole());
        user.setMenu(list);
    }
}
