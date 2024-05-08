package hongs.community.hongsCommunity.domain.disable.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserDisableDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-08
* @summary  사용자 계정 비활성화 dto
**/

@Getter @Setter
public class HongUserDisableDto extends CreateRequest {

    private Long userUid;
    private String disableMsg;
}
