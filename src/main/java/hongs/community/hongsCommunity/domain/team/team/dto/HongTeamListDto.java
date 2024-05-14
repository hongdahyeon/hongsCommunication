package hongs.community.hongsCommunity.domain.team.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongTeamListDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 리스트 조회 dto
**/

@Getter @Setter
@NoArgsConstructor
public class HongTeamListDto {

    private Long loginUser;
    private String search;

    public HongTeamListDto(Long loginUser, String search) {
        this.loginUser = loginUser;
        this.search = search;
    }
}
