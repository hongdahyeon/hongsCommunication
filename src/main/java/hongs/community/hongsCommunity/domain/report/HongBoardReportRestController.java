package hongs.community.hongsCommunity.domain.report;


import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportInsertUpdateDto;
import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportListVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin/board/report")
@RequiredArgsConstructor
@Tag(name = "게시판 유형 관리", description = "ADMIN 권한에서 접근이 가능하며, 게시판 유형을 관리한다.")
public class HongBoardReportRestController {

    private final HongBoardReportService boardReportService;

    @GetMapping("/list.json")
    @Operation(summary = "게시글 리스트 조회", description = "게시글 리스트를 조회한다.")
    @ApiDocumentResponse
    public Response list(HongBoardReportListDto dto) {
        List<HongBoardReportListVo> list = boardReportService.list(dto);
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "게시글 단건 저장", description = "게시글 단건 저장")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongBoardReportInsertUpdateDto dto){
        Integer insert = boardReportService.insert(dto);
        return (insert == 1) ? Response.created() : Response.badRequest();
    }


    @PutMapping("/update.json")
    @Operation(summary = "게시글 단건 수정", description = "게시글 단건 수정")
    @ApiDocumentResponse
    public Response update(@RequestBody HongBoardReportInsertUpdateDto dto){
        Integer update = boardReportService.update(dto);
        return (update == 1) ? Response.ok() : Response.badRequest();
    }
}
