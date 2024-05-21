package hongs.community.hongsCommunity.domain.code.vo;


import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUpperCodeListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  상위 코드 리스트 반환용 vo
**/

@Getter @Setter
public class HongUpperCodeListVo {

    private Long hongCodeUid;
    private String codeVal;
    private String codeNm;
    private Integer childCodeCnt;
    private String useYn;
    private String regName;
    private String regDt;
}
