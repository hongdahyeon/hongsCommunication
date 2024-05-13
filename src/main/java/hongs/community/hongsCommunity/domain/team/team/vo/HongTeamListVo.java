package hongs.community.hongsCommunity.domain.team.team.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongTeamListVo {

    private Long hongTeamUid;
    private String teamCategory;
    private String teamCategoryName;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private Long teamProfile;
    private String teamProfileUrl;
    private Long representId;
    private String representName;
    private String approvalYn;
    private String useYn;
    private String deleteYn;
    private Integer totalMemberNum;
}
