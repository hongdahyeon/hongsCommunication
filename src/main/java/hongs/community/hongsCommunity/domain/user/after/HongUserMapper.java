package hongs.community.hongsCommunity.domain.user.after;

import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUpdteDto;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserCheckEmailVo;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongUserMapper {

    HongUserInfoVo userInfo(Long userUid);

    Integer chkUserEmail(HongUserCheckEmailVo checkEmailVo);

    void updateUser(HongUserUpdteDto dto);
}
