package hongs.community.hongsCommunity.domain.bbs.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsTypeInsertUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  게시판 유형 등록 및 수정 dto
**/

@Getter @Setter
public class HongBbsTypeInsertUpdateDto extends CreateRequest {

    private Long hongBbsTypeUid;
    private String bbsNm;
    private String bbsTypeCd;
    private String cmntUseYn;
    private String fileAtchYn;
    private String useYn;

}
