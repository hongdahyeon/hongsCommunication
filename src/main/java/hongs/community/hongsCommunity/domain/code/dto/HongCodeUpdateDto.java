package hongs.community.hongsCommunity.domain.code.dto;


import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCodeUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  단일 코드 수정용 dto
**/

@Getter @Setter
public class HongCodeUpdateDto extends UpdateRequest {

    private Long hongCodeUid;
    private String codeName;
    private String memoCn;
    private String useYn;

}
