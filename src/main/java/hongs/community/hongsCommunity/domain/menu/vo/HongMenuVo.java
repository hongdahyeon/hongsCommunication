package hongs.community.hongsCommunity.domain.menu.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HongMenuVo {

    private Long menuUid;
    private Long upperMenuUid;
    private String menuUrl;
    private String menuText;
    private String menuRole;
    private String useYn;
    private String deleteYn;
    private Integer sortNum;

    private List<HongMenuVo> children;
}

