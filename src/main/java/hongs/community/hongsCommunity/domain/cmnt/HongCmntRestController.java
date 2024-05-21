package hongs.community.hongsCommunity.domain.cmnt;


import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntListDto;
import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntInsertDto;
import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntUpdateDto;
import hongs.community.hongsCommunity.domain.cmnt.vo.HongCmntListVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cmnt")
@Tag(name = "댓글 RestController", description = "댓글 RestController")
public class HongCmntRestController {

    private final HongCmntService cmntService;

    @GetMapping("/list.json")
    @Operation(summary = "댓글 리스트 조회", description = "특정 type, type-id 하위의 댓글 리스트 조회")
    @ApiDocumentResponse
    public Response list(HongCmntListDto dto){
        List<HongCmntListVo> list = cmntService.list(dto);
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "댓글 등록", description = "특정 type, type-id 하위로 댓글 등록")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongCmntInsertDto dto) {
        Integer insert = cmntService.insert(dto);
        return (insert == 1) ? Response.created() : Response.badRequest();
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "댓글 삭제", description = "특정 type, type-id 하위의 댓글 삭제")
    @ApiDocumentResponse
    public Response delete(@RequestParam(required = true, name = "uid") Long uid) {
        Integer delete = cmntService.delete(uid);
        return (delete == 1) ? Response.ok() : Response.badRequest();
    }

    @PutMapping("/update.json")
    @Operation(summary = "댓글 수정", description = "특정 type, type-id 하위의 댓글 수정")
    @ApiDocumentResponse
    public Response update(@RequestBody HongCmntUpdateDto dto){
        Integer update = cmntService.update(dto);
        return (update == 1) ? Response.ok() : Response.badRequest();
    }
}
