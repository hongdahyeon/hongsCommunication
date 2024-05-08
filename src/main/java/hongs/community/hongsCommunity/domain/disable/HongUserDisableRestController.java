package hongs.community.hongsCommunity.domain.disable;

import hongs.community.hongsCommunity.domain.disable.dto.HongUserDisableDto;
import hongs.community.hongsCommunity.domain.disable.dto.HongUserEnableDto;
import hongs.community.hongsCommunity.domain.disable.vo.HongUserDisableViewVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/user-disable")
@Tag(name = "사용자 비활성화 RestController", description = "관리자(admin) 권한에서 사용자의 계정을 비활성화 및 활성화")
public class HongUserDisableRestController {

    private final HongUserDisableService disableService;

    @GetMapping("/view.json")
    @Operation(summary = "비활성화된 사유 및 정보 보기", description = "비활성화된 사용자의 사유 및 정보 보기")
    @ApiDocumentResponse
    public Response view(@RequestParam(required = true, name = "userUid") Long userUid) {
        HongUserDisableViewVo view = disableService.view(userUid);
        return Response.ok(view);
    }

    @PutMapping("/to-enable.json")
    @Operation(summary = "비활성화된 사용자의 계정 활성화", description = "비활성화된 사용자의 계정 활성화")
    @ApiDocumentResponse
    public Response toEnable(@RequestBody HongUserEnableDto dto) {
        Integer enable = disableService.toEnable(dto);
        return (enable >= 2) ? Response.ok() : Response.badRequest();
    }

    @PostMapping("/to-disable.json")
    @Operation(summary = "사용자의 계정 비활성화", description = "사용자의 계정 비활성화")
    @ApiDocumentResponse
    public Response toDisable(@RequestBody HongUserDisableDto dto) {
        Integer disable = disableService.toDisable(dto);
        return (disable >= 2) ? Response.ok() : Response.badRequest();
    }
}
