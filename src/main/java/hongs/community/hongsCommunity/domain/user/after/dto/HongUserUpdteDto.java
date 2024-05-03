package hongs.community.hongsCommunity.domain.user.after.dto;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserUpdteDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  회원정보 변경 dto
**/

@Getter @Setter
public class HongUserUpdteDto {

    private Long userUid;
    private String userName;
    private String password;
    private String userEmail;
}
