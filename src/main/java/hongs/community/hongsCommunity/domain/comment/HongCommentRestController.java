package hongs.community.hongsCommunity.domain.comment;


import hongs.community.hongsCommunity.domain.comment.dto.HongCommentListDto;
import hongs.community.hongsCommunity.domain.comment.dto.HongCommentInsertDto;
import hongs.community.hongsCommunity.domain.comment.dto.HongCommentUpdateDto;
import hongs.community.hongsCommunity.domain.comment.vo.HongCommentListVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
@Tag(name = "댓글 RestController", description = "댓글 RestController")
public class HongCommentRestController {

    private final HongCommentService commentService;

    @GetMapping("/list.json")
    @Operation(summary = "댓글 리스트 조회", description = "특정 type, type-id 하위의 댓글 리스트 조회")
    @ApiDocumentResponse
    public Response list(HongCommentListDto dto){
        List<HongCommentListVo> list = commentService.list(dto);
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "댓글 등록", description = "특정 type, type-id 하위로 댓글 등록")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongCommentInsertDto dto) {
        Integer insert = commentService.insert(dto);
        return (insert == 1) ? Response.created() : Response.badRequest();
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "댓글 삭제", description = "특정 type, type-id 하위의 댓글 삭제")
    @ApiDocumentResponse
    public Response delete(@RequestParam(required = true, name = "uid") Long uid) {
        Integer delete = commentService.delete(uid);
        return (delete == 1) ? Response.ok() : Response.badRequest();
    }

    @PutMapping("/update.json")
    @Operation(summary = "댓글 수정", description = "특정 type, type-id 하위의 댓글 수정")
    @ApiDocumentResponse
    public Response update(@RequestBody HongCommentUpdateDto dto){
        Integer update = commentService.update(dto);
        return (update == 1) ? Response.ok() : Response.badRequest();
    }
}
