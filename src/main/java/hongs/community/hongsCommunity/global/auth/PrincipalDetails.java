package hongs.community.hongsCommunity.global.auth;

import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import hongs.community.hongsCommunity.global.util.TimeUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {

    private final HongLoginUserVo hongUser;
    private Map<String, Object> attributes;

    public PrincipalDetails(HongLoginUserVo user) {
        this.hongUser = user;
    }

    public HongLoginUserVo getUser() {
        return hongUser;
    }

    public PrincipalDetails(HongLoginUserVo user, Map<String, Object> attributes){
        this.hongUser = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority(hongUser.getRole()));
        return collection;
    }

    @Override
    public String getPassword() {
        return hongUser.getPassword();
    }

    @Override
    public String getUsername() {
        return hongUser.getUserName();
    }

    /* 계정 만료 여부 : 마지막 로그인 날짜가 1년이 지났는지 체크 */
    @Override
    public boolean isAccountNonExpired() {
        return TimeUtil.isXYearAfter(hongUser.getLastLoginDate(), 1);
    }

    /* 계정 잠김 여부 */
    @Override
    public boolean isAccountNonLocked() {
        return hongUser.getIsNonLocked();
    }

    /* 비밀번호 만료 여부 : 만료일이 오늘을 지났는지 (비밀번호 변경일은 변경일로부터 90일) */
    @Override
    public boolean isCredentialsNonExpired() {
        return TimeUtil.dateCompare(hongUser.getPwdLastUpdate());
    }

    /* 계정 활성화 여부 */
    @Override
    public boolean isEnabled() {
        return hongUser.getIsEnable();
    }
}
