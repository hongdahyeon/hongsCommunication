package hongs.community.hongsCommunity.domain.user.after.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.FileRequest;
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
public class HongUserUpdteDto extends FileRequest {

    private Long userUid;
    private String userNm;
    private String password;
    private String userEmail;
    private Long profile;
}
