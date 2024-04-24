package hongs.community.hongsCommunity.global.hongs.file.common;

import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import hongs.community.hongsCommunity.global.hongs.file.common.vo.HongFileViewVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/hong/files")
@RestController
@RequiredArgsConstructor
@Tag(name = "File RestController", description = "첨부파일에 관련된 RestController")
public class HongCommonFileRestController {

    private final HongCommonFileService hongCommonFileService;

    @GetMapping("list.json")
    @Operation(summary = "파일 리스트", description = "동일한 fileUid를 갖는 파일 리스트를 가져온다.")
    @ApiDocumentResponse
    public Response list(@RequestParam(name = "fileUid") Long fileUid) {
        List<HongFileViewVo> list = hongCommonFileService.list(fileUid);
        return Response.ok(list);
    }

    @GetMapping("download")
    @Operation(summary = "파일 다운로드", description = "fileUrl를 통해 특정 파일을 다운로드 한다.(downCnt 증가, 로그 남기기)")
    @ApiDocumentResponse
    public ResponseEntity<Resource> download(final HttpServletRequest req, final HttpServletResponse res) {
        return hongCommonFileService.download(req);
    }
}
