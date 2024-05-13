package hongs.community.hongsCommunity.domain.menu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @fileName HongMenuSuperVo
* @author dahyeon
* @version 1.0.0
* @date 2024-04-22
* @summary super 권한에서 메뉴 변경을 위해 불러오는 메뉴 vo
**/

@Getter @Setter
public class HongMenuSuperVo {

    private Long menuUid;
    private Long upperMenuUid;
    private String menuText;
    private String menuUrl;
    private String menuRole;
    private String useYn;
    private String deleteYn;
    private String landingPageYn;
    private Integer sortNo;
    private String menuPath;

    private List<HongMenuVo> children;
}
