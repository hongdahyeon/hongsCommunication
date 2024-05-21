package hongs.community.hongsCommunity.domain.postAprs;


import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPostAprsDeleteDto;
import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPstAprsInsertDto;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post/aprs")
@Tag(name = "게시글 감정 RestController", description = "게시글에 달린 감정 이모티콘 RestController")
public class HongBbsPstAprsRestController {

    private final HongBbsPstAprsService bbsPstAprsService;

    @PostMapping("/insert.json")
    @Operation(summary = "게시글 감정 등록", description = "게시글 감정을 등록한다.")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongBbsPstAprsInsertDto dto){
        Integer insert = bbsPstAprsService.insert(dto);
        return Response.created(insert);
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "게시글 감정 삭제", description = "게시글 감정을 삭제한다.")
    @ApiDocumentResponse
    public Response delete(HongBbsPostAprsDeleteDto dto){
        Integer delete = bbsPstAprsService.delete(dto);
        return Response.ok(delete);
    }

}
