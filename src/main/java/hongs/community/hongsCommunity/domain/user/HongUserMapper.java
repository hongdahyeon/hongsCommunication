package hongs.community.hongsCommunity.domain.user;

import hongs.community.hongsCommunity.domain.user.dto.HongSocialUserInsertDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserUpdateFailCntDto;
import hongs.community.hongsCommunity.domain.user.vo.HongCheckUserVo;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongUserMapper {

    HongLoginUserVo findUser(String userId);

    HongLoginUserVo findSocialUser(String userId);

    Integer joinUser(HongUserInsertDto dto);

    void joinSocialUser(HongSocialUserInsertDto dto);

    HongCheckUserVo checkUser(String userId);

    void updatePwdFailCnt(HongUserUpdateFailCntDto dto);

    void resetFailCnt(String userId);

    HongLoginUserVo findUserByUid(Long userUid);
}
