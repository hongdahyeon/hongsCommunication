package hongs.community.hongsCommunity.domain.code.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCodeDeleteDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  단일 코드 삭제용 dto
**/


@Getter @Setter
public class HongCodeDeleteDto extends UpdateRequest {

    private Long hongCodeUid;
}
