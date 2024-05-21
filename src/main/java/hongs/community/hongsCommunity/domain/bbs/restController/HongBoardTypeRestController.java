package hongs.community.hongsCommunity.domain.bbs.restController;


import hongs.community.hongsCommunity.domain.bbs.HongBbsTypeService;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeViewVo;
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
@RequestMapping("api/bbs/type")
@RequiredArgsConstructor
@Tag(name = "USER 권한 > 게시판 유형 조회", description = "USER 권한에서 접근이 가능하며, 게시판 유형을 조회한다.")
public class HongBoardTypeRestController {

    private final HongBbsTypeService bbsTypeService;

    @GetMapping("/view.json")
    @Operation(summary = "게시판 유형 단건 조회", description = "게시판 유형을 단건 조회한다.")
    @ApiDocumentResponse
    public Response view(@RequestParam(required = true, name = "hongBbsTypeUid") Long hongBbsTypeUid) {
        HongBbsTypeViewVo view = bbsTypeService.view(hongBbsTypeUid);
        return Response.ok(view);
    }
}
