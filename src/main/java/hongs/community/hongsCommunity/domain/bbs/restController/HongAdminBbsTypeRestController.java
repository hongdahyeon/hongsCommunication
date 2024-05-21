package hongs.community.hongsCommunity.domain.bbs.restController;


import hongs.community.hongsCommunity.domain.bbs.HongBbsTypeService;
import hongs.community.hongsCommunity.domain.bbs.dto.HongBbsTypeInsertUpdateDto;
import hongs.community.hongsCommunity.domain.bbs.dto.HongBbsYnUpdateDto;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeListVo;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeViewVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/bbs/type")
@RequiredArgsConstructor
@Tag(name = "ADMIN 권한 > 게시판 유형 관리", description = "ADMIN 권한에서 접근이 가능하며, 게시판 유형을 관리한다.")
public class HongAdminBbsTypeRestController {

    private final HongBbsTypeService bbsTypeService;

    @GetMapping("/list.json")
    @Operation(summary = "게시판 유형 리스트 조회", description = "게시판 유형 리스트를 조회한다.")
    @ApiDocumentResponse
    public Response list() {
        List<HongBbsTypeListVo> list = bbsTypeService.list();
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "게시판 유형 추가", description = "게시판 유형을 추가한다.")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongBbsTypeInsertUpdateDto dto) {
        Integer insert = bbsTypeService.insert(dto);
        return (insert == 1) ? Response.created() : Response.badRequest();
    }

    @GetMapping("/view.json")
    @Operation(summary = "게시판 유형 단건 조회", description = "게시판 유형을 단건 조회한다.")
    @ApiDocumentResponse
    public Response view(@RequestParam(required = true, name = "hongBbsTypeUid") Long hongBbsTypeUid) {
        HongBbsTypeViewVo view = bbsTypeService.view(hongBbsTypeUid);
        return Response.ok(view);
    }

    @PutMapping("/update.json")
    @Operation(summary = "게시판 유형 단건 수정", description = "게시판 유형을 단건 수정한다.")
    @ApiDocumentResponse
    public Response update(@RequestBody HongBbsTypeInsertUpdateDto dto) {
        Integer update = bbsTypeService.update(dto);
        return (update == 1) ? Response.ok() : Response.badRequest();
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "게시판 유형 단건 삭제", description = "게시판 유형을 단건 삭제한다.")
    @ApiDocumentResponse
    public Response delete(@RequestParam(required = true, name = "hongBbsTypeUid") Long hongBbsTypeUid){
        Integer delete = bbsTypeService.delete(hongBbsTypeUid);
        return (delete == 1) ? Response.ok() : Response.badRequest();
    }

    @PutMapping("/ynUpdate.json")
    @Operation(summary = "게시판 유형 - yn 값들 수정", description = "게시판 유형 - yn 값들 수정 : commentYn, fileAtchYn, useYn")
    @ApiDocumentResponse
    public Response ynUpdate(@RequestBody HongBbsYnUpdateDto dto) {
        Integer ynUpdate = bbsTypeService.ynUpdate(dto);
        return (ynUpdate == 1) ? Response.ok() : Response.badRequest();
    }
}
