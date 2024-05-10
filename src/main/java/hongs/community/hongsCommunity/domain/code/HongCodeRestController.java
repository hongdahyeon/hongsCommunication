package hongs.community.hongsCommunity.domain.code;


import hongs.community.hongsCommunity.domain.code.dto.HongCodeDeleteDto;
import hongs.community.hongsCommunity.domain.code.dto.HongCodeInsertDto;
import hongs.community.hongsCommunity.domain.code.dto.HongCodeUpdateDto;
import hongs.community.hongsCommunity.domain.code.vo.HongCodeViewVo;
import hongs.community.hongsCommunity.domain.code.vo.HongUpperCodeListVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/super/code")
@RequiredArgsConstructor
@Tag(name = "SUPER 권한: 코드 RestController", description = "SUPER 권한에서 접근이 가능하며, 코드를 관리한다.")
public class HongCodeRestController {

    private final HongCodeService hongCodeService;

    @GetMapping("/upper-list.json")
    @Operation(summary = "상위코드 리스트", description = "상위코드 리스트를 가져온다.")
    @ApiDocumentResponse
    public Response upperList() {
        List<HongUpperCodeListVo> upperList = hongCodeService.upperList();
        return Response.ok(upperList);
    }

    @GetMapping("/check-duplicate.json")
    @Operation(summary = "코드명 중복 확인", description = "사용하려는 코드명이 중복되는지 확인한다.")
    @ApiDocumentResponse
    public Response checkDuplicate(@RequestParam(required = true, name = "codeVal") String codeVal) {
        Boolean ifDuplicated = hongCodeService.checkIfDuplicate(codeVal);
        return (ifDuplicated) ? Response.badRequest() : Response.ok();
    }

    @PostMapping("/insert.json")
    @Operation(summary = "상위코드 추가", description = "상위코드를 추가한다.")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongCodeInsertDto dto) {
        Integer codeInsert = hongCodeService.insert(dto);
        return (codeInsert == 1) ? Response.created() : Response.badRequest();
    }

    @GetMapping("/upperView.json")
    @Operation(summary = "상위 코드 상세보기", description = "단일 상위 코드 정보를 가져온다.")
    @ApiDocumentResponse
    public Response view(@RequestParam(required = true, name = "hongCodeUid") Long hongCodeUid){
        HongCodeViewVo view = hongCodeService.upperView(hongCodeUid);
        return Response.ok(view);
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "단일 코드 삭제", description = "단일 코드 정보를 삭제한다. 단, 상위코드의 경우 하위자식코드가 없을 경우에만 삭제할 수 있다.")
    @ApiDocumentResponse
    public Response delete(HongCodeDeleteDto dto) {
        Integer delete = hongCodeService.delete(dto);
        return (delete == 1) ? Response.ok() : Response.badRequest();
    }

    @PutMapping("/update.json")
    @Operation(summary = "단일 코드 수정", description = "단일 코드 정보를 수정한다.")
    @ApiDocumentResponse
    public Response update(@RequestBody HongCodeUpdateDto dto) {
        Integer update = hongCodeService.update(dto);
        return (update == 1) ? Response.ok() : Response.badRequest();
    }
}
