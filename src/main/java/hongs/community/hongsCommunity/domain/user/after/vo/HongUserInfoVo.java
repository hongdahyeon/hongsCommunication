package hongs.community.hongsCommunity.domain.user.after.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserInfoVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  회원정보 조회 및 변경을 위한 vo
**/

@Getter
@Setter
public class HongUserInfoVo {

    private Long userUid;
    private String userId;
    private String userName;
    private String userEmail;
    private String role;
    private String roleName;
    private Long profile;
}
