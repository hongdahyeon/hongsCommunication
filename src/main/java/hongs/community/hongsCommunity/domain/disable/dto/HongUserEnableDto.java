package hongs.community.hongsCommunity.domain.disable.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserEnableDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-08
* @summary  사용자 계정 활성화 dto
**/

@Getter @Setter
public class HongUserEnableDto extends UpdateRequest {

    private Long userUid;
}
