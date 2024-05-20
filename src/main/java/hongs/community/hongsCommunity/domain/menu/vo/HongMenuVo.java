package hongs.community.hongsCommunity.domain.menu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
* @fileName HongMenuVo
* @author dahyeon
* @version 1.0.0
* @date 2024-04-22
* @summary  화면단에 메뉴를 뿌려주기 위한 vo
**/

@Getter @Setter
public class HongMenuVo {

    private Long menuUid;
    private Long upperMenuUid;
    private String menuUrl;
    private String menuNm;
    private String menuRole;
    private String useYn;
    private String delYn;
    private Integer sortNo;

    private List<HongMenuVo> children;
    private List<String> urlList = new ArrayList<>();
}

