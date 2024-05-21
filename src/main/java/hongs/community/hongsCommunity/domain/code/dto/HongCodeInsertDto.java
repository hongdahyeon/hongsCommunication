package hongs.community.hongsCommunity.domain.code.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCodeInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  단일 코드 추가용 dto
**/

@Getter @Setter
public class HongCodeInsertDto extends CreateRequest {

    private String codeVal;
    private String codeNm;
    private String memoCn;
    private String useYn;
}
