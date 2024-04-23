package hongs.community.hongsCommunity.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongMenuDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-23
* @summary  조회용 dto
**/

@Getter @Setter @NoArgsConstructor
public class HongMenuDto {

    private Long menuUid;
    private Long upperMenuUid;
    private String menuRole;
    private String useYn;
    private String deleteYn;

    public HongMenuDto(String menuRole, String useYn, String deleteYn) {
        this.menuRole = menuRole;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
    public HongMenuDto(Long upperMenuUid, String useYn, String deleteYn) {
        this.upperMenuUid = upperMenuUid;
        this.useYn = useYn;
        this.deleteYn = deleteYn;
    }
}
