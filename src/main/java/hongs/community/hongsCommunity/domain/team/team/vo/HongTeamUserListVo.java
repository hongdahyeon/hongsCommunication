package hongs.community.hongsCommunity.domain.team.team.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongTeamUserListVo {

    private Long hongTeamUserUid;
    private Long hongTeamUid;
    private String teamNm;
    private Long userUid;
    private String userName;
    private String userEmail;
    private String userRole;
    private String joinDate;
    private String approvalYn;
    private String approvalYnStr;
}
