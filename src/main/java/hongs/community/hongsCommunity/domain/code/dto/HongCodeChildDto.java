package hongs.community.hongsCommunity.domain.code.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCodeChildDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  자식 코드 저장 및 수정
**/

@Getter @Setter
public class HongCodeChildDto {

    private Long hongCodeUid;
    private String codeVal;
    private String codeName;
    private String memoCn;
    private String useYn;

}
