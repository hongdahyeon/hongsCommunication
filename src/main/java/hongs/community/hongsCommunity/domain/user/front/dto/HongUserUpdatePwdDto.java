package hongs.community.hongsCommunity.domain.user.front.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongUserUpdatePwdDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  로그인 이전, 비밀번호 초기화 (랜덤 6자리)
**/

@Getter @Setter @NoArgsConstructor
public class HongUserUpdatePwdDto {

    private String password;
    private String userEmail;
    private String userNm;

    public HongUserUpdatePwdDto(String password, HongSearchIdPwdDto dto){
        this.password = password;
        this.userEmail = dto.getUserEmail();
        this.userNm = dto.getUserNm();
    }
}
