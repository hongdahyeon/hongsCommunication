package hongs.community.hongsCommunity.domain.team.admin;

import hongs.community.hongsCommunity.domain.team.admin.dto.HongTeamAdminChangeDto;
import hongs.community.hongsCommunity.domain.team.admin.vo.HongTeamAdminListVo;
import hongs.community.hongsCommunity.domain.team.admin.vo.HongTeamUserAdminListVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/admin/team")
@Tag(name = "팀 관리 RestController", description = "admin 권한에서 팀 관리하는 RestController")
public class HongTeamAdminRestController {

    private final HongTeamAdminService teamAdminService;

    @GetMapping("/list.json")
    @Operation(summary = "팀 리스트 조회", description = "admin 권한에서 팀 리스트 조회")
    @ApiDocumentResponse
    public Response list() {
        List<HongTeamAdminListVo> list = teamAdminService.list();
        return Response.ok(list);
    }

    @PutMapping("/change-approval.json")
    @Operation(summary = "팀 승인상태 변경하기", description = "admin 권한에서 팀 승인상태 변경하기")
    @ApiDocumentResponse
    public Response changeApproval(@RequestBody HongTeamAdminChangeDto dto){
        Integer changedApproval = teamAdminService.changeApproval(dto);
        return (changedApproval == 1) ? Response.ok() : Response.badRequest();
    }

    @PutMapping("/change-use.json")
    @Operation(summary = "팀 승인상태 변경하기", description = "admin 권한에서 팀 승인상태 변경하기")
    @ApiDocumentResponse
    public Response changeUse(@RequestBody HongTeamAdminChangeDto dto){
        Integer changedUse = teamAdminService.changeUse(dto);
        return (changedUse == 1) ? Response.ok() : Response.badRequest();
    }

    @GetMapping("/team-user-list.json")
    @Operation(summary = "팀의 멤버 리스트 조회", description = "admin 권한에서 팀의 멤버 리스트 조회")
    @ApiDocumentResponse
    public Response teamUserList(@RequestParam(required = true, name = "hongTeamUid") Long hongTeamUid) {
        List<HongTeamUserAdminListVo> teamUserList = teamAdminService.teamUserList(hongTeamUid);
        return Response.ok(teamUserList);
    }
}
