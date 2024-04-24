package hongs.community.hongsCommunity.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class HongUserUpdatePwdDto {

    private String password;
    private String userEmail;
    private String userName;

    public HongUserUpdatePwdDto(String password, HongSearchIdPwdDto dto){
        this.password = password;
        this.userEmail = dto.getUserEmail();
        this.userName = dto.getUserName();
    }
}
