package hongs.community.hongsCommunity.domain.user.dto;


import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserChngPwdDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-26
* @summary  사용자가 비밀번호를 90일 연장하거나,   (chngPwd : false)
 *                 비밀번호를 변경한다. (chngPwd : true)
**/

@Getter @Setter
public class HongUserChngPwdDto {

    private String userId;
    private Boolean chngPwd;
    private String password;
    private String pwdLastUpdate = TimeUtil.daysAfter(90);
}
