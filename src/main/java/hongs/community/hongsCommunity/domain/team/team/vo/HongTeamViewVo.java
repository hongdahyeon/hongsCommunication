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
    private String teamCategory;
    private String teamCategoryName;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private String teamIntro;
    private Long teamProfile;
    private String teamProfileUrl;
    private Long representId;
    private String representName;
    private String approvalYn;
    private String useYn;
    private String deleteYn;
    private Integer totalMemberNum;
    private String joinedStatus;
    private Boolean ifJoined;
}
