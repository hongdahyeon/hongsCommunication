package hongs.community.hongsCommunity.domain.menu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongMenuSuperDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-23
* @summary  super 권한에서 메뉴 변경을 위해 불러오는 메뉴 dto
**/

@Getter @Setter
@NoArgsConstructor
public class HongMenuSuperDto {

    private String menuRole;
    private String useYn;

    public HongMenuSuperDto(String menuRole, String useYn) {
        this.menuRole = menuRole;
        this.useYn = useYn;
    }
}
