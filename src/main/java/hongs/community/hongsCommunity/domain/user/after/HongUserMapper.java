package hongs.community.hongsCommunity.domain.user.after;

import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUpdteDto;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserCheckEmailVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserInfoVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongUserMapper {

    HongUserInfoVo userInfo(Long userUid);

    Integer chkUserEmail(HongUserCheckEmailVo checkEmailVo);

    void updateUser(HongUserUpdteDto dto);

    List<HongUserListVo> list();
}
