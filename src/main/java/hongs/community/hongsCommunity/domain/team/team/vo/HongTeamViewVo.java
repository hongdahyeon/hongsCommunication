package hongs.community.hongsCommunity.domain.team.team.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongTeamViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 단건 조회
**/

@Getter @Setter
public class HongTeamViewVo {

    private Long hongTeamUid;
    private String teamCtgry;
    private String teamCtgryNm;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private String teamIntro;
    private Long teamProfile;
    private String teamProfileUrl;
    private Long rprsvUid;
    private String rprsvNm;
    private String aprvYn;
    private String useYn;
    private String delYn;
    private Integer totalMemberNum;
    private String joinedStatus;
    private Boolean ifJoined;
}
