package hongs.community.hongsCommunity.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserSendEmailDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-26
* @summary  1년간 로그인을 안하여 휴먼계정이된 경우, 이메일 인증을 통해 풀어야 한다.
**/

@Getter @Setter
public class HongUserSendEmailDto {

    private String userId;
    private String userEmail;
}
