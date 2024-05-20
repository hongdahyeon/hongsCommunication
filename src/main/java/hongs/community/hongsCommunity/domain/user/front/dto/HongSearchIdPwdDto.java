package hongs.community.hongsCommunity.domain.user.front.dto;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongSearchIdPwdDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  로그인 이전, 이메일과 이름으로 아이디 찾기 / 비밀번호 초기화
**/

@Getter @Setter
public class HongSearchIdPwdDto {

    private String userEmail;
    private String userNm;
}