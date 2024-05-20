package hongs.community.hongsCommunity.domain.menu.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongMenuInsertUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-23
* @summary super 권한에서 메뉴 추가 및 수정 dto
**/
@Getter @Setter
public class HongMenuInsertUpdateDto extends CreateRequest {

    private Long menuUid;
    private Long upperMenuUid;
    private String menuNm;
    private String menuUrl;
    private String useYn;
    private String landingPageYn;
    private String menuRole;
    private Integer sortNo;
}
