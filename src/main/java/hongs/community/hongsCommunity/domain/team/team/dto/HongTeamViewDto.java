package hongs.community.hongsCommunity.domain.team.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongTeamViewDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 단건 조회
 *          -> loginUser 용도 : 현재 로그인 유저가 hongTeamUid에 참여되어있는지 체크
**/

@Getter @Setter
@NoArgsConstructor
public class HongTeamViewDto {

    private Long hongTeamUid;
    private Long loginUser;

    public HongTeamViewDto(Long hongTeamUid, Long loginUser) {
        this.hongTeamUid = hongTeamUid;
        this.loginUser = loginUser;
    }
}
