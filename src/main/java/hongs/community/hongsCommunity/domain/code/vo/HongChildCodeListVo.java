package hongs.community.hongsCommunity.domain.code.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongChildCodeListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  자식 코드 리스트 조회용 vo
**/

@Getter @Setter
public class HongChildCodeListVo {

    private Long hongCodeUid;
    private String codeVal;
    private String codeName;
    private String memoCn;
    private String useYn;
}
