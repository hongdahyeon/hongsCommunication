package hongs.community.hongsCommunity.domain.code.vo;


import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCodeViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary  단일 코드 view용 dto
**/

@Getter @Setter
public class HongCodeViewVo {

    private Long hongCodeUid;
    private String upperCodeVal;
    private String codeVal;
    private String codeNm;
    private String memoCn;
    private String useYn;
    private Integer childCodeCnt;

}
