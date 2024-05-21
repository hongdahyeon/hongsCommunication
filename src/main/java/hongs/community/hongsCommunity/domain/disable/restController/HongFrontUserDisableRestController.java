package hongs.community.hongsCommunity.domain.disable.restController;

import hongs.community.hongsCommunity.domain.disable.HongUserDisableService;
import hongs.community.hongsCommunity.domain.disable.vo.HongUserDisableViewVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/front/user-disable")
@Tag(name = "사용자 비활성화 RestController", description = "로그인 이전, 사용자 비활성화 사유를 가져오기 위한 RestController")
public class HongFrontUserDisableRestController {

    private final HongUserDisableService disableService;
    @GetMapping("/viewMsg.json")
    @Operation(summary = "비활성화된 사유 및 정보 보기", description = "비활성화된 사용자의 사유 및 정보 보기")
    @ApiDocumentResponse
    public Response viewMsg(@RequestParam(required = true, name = "userId") String userId) {
        HongUserDisableViewVo view = disableService.viewMsg(userId);
        return Response.ok(view);
    }
}
