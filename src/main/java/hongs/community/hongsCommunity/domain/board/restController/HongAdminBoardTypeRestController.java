package hongs.community.hongsCommunity.domain.board.restController;


import hongs.community.hongsCommunity.domain.board.HongBoardTypeService;
import hongs.community.hongsCommunity.domain.board.dto.HongBoardTypeInsertUpdateDto;
import hongs.community.hongsCommunity.domain.board.dto.HongBoardYnUpdateDto;
import hongs.community.hongsCommunity.domain.board.vo.HongBoardTypeListVo;
import hongs.community.hongsCommunity.domain.board.vo.HongBoardTypeViewVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/board/type")
@RequiredArgsConstructor
@Tag(name = "ADMIN 권한 > 게시판 유형 관리", description = "ADMIN 권한에서 접근이 가능하며, 게시판 유형을 관리한다.")
public class HongAdminBoardTypeRestController {

    private final HongBoardTypeService boardTypeService;

    @GetMapping("/list.json")
    @Operation(summary = "게시판 유형 리스트 조회", description = "게시판 유형 리스트를 조회한다.")
    @ApiDocumentResponse
    public Response list() {
        List<HongBoardTypeListVo> list = boardTypeService.list();
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "게시판 유형 추가", description = "게시판 유형을 추가한다.")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongBoardTypeInsertUpdateDto dto) {
        Integer insert = boardTypeService.insert(dto);
        return (insert == 1) ? Response.created() : Response.badRequest();
    }

    @GetMapping("/view.json")
    @Operation(summary = "게시판 유형 단건 조회", description = "게시판 유형을 단건 조회한다.")
    @ApiDocumentResponse
    public Response view(@RequestParam(required = true, name = "hongBoardTypeUid") Long hongBoardTypeUid) {
        HongBoardTypeViewVo view = boardTypeService.view(hongBoardTypeUid);
        return Response.ok(view);
    }

    @PutMapping("/update.json")
    @Operation(summary = "게시판 유형 단건 수정", description = "게시판 유형을 단건 수정한다.")
    @ApiDocumentResponse
    public Response update(@RequestBody HongBoardTypeInsertUpdateDto dto) {
        Integer update = boardTypeService.update(dto);
        return (update == 1) ? Response.ok() : Response.badRequest();
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "게시판 유형 단건 삭제", description = "게시판 유형을 단건 삭제한다.")
    @ApiDocumentResponse
    public Response delete(@RequestParam(required = true, name = "hongBoardTypeUid") Long hongBoardTypeUid){
        Integer delete = boardTypeService.delete(hongBoardTypeUid);
        return (delete == 1) ? Response.ok() : Response.badRequest();
    }

    @PutMapping("/ynUpdate.json")
    @Operation(summary = "게시판 유형 - yn 값들 수정", description = "게시판 유형 - yn 값들 수정 : commentYn, fileAtchYn, useYn")
    @ApiDocumentResponse
    public Response ynUpdate(@RequestBody HongBoardYnUpdateDto dto) {
        Integer ynUpdate = boardTypeService.ynUpdate(dto);
        return (ynUpdate == 1) ? Response.ok() : Response.badRequest();
    }
}
