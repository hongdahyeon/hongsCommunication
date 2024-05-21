package hongs.community.hongsCommunity.domain.team.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongTeamUserInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 멤버 추가 dto
 *          -> 대표만 aprvYn Y 로 insert
 *          -> 나머지 팀원들은 aprvYn N으로 insert
**/

@Getter @Setter
@NoArgsConstructor
public class HongTeamUserInsertDto {

    private Long userUid;
    private Long hongTeamUid;
    private String aprvYn;

    public HongTeamUserInsertDto(Long userUid, Long hongTeamUid, String aprvYn) {
        this.userUid = userUid;
        this.hongTeamUid = hongTeamUid;
        this.aprvYn = aprvYn;
    }

}
