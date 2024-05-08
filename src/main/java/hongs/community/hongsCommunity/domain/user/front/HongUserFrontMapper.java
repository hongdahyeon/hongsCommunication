package hongs.community.hongsCommunity.domain.user.front;

import hongs.community.hongsCommunity.domain.user.front.dto.*;
import hongs.community.hongsCommunity.domain.user.front.vo.HongCheckUserVo;
import hongs.community.hongsCommunity.domain.user.front.vo.HongLoginUserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongUserFrontMapper {

    HongLoginUserVo findUser(String userId);

    HongLoginUserVo findSocialUser(String userId);

    Integer joinUser(HongUserInsertDto dto);

    HongCheckUserVo checkUser(String userId);

    void updatePwdFailCnt(HongUserUpdateFailCntDto dto);

    void resetFailCntAndLastLoginDate(String userId);

    HongLoginUserVo findUserByUid(Long userUid);

    void joinSocialUser(HongSocialUserInsertDto dto);

    String searchId(HongSearchIdPwdDto dto);

    void updateUserPwd(HongUserUpdatePwdDto dto);

    Integer chngPwd(HongUserChngPwdDto dto);

    Integer searchUser(HongUserSendEmailDto dto);

    Integer chkUserId(String userId);

    Integer chkUserEmail(String userEmail);

    void updatePwdChangeDateAndLoginDate(String userEmail);
}
