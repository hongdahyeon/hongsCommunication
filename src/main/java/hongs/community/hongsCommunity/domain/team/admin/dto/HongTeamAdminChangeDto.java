package hongs.community.hongsCommunity.domain.team.admin.dto;


import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongTeamAdminChangeDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-13
* @summary  admin 권한에서 팀의 사용여부/승인여부 변경하기
**/

@Getter @Setter
public class HongTeamAdminChangeDto extends UpdateRequest {

    private Long hongTeamUid;
    private String approvalYn;
    private String useYn;
}
