package hongs.community.hongsCommunity.global.hongs.file.tus;

import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/tus/files")
@RestController
@RequiredArgsConstructor
@Tag(name = "Tus RestController", description = "첨부파일에 관련된 RestController - tus")
public class HongTusFileRestController {

    private final HongTusFileService tusFileService;
    private final TusFileUploadService tusFileUploadService;

    @DeleteMapping("temp/delete.json")
    @Operation(summary = "임시파일 삭제", description = "fileUrl을 통해 아직 db에 저장되지 않은 임시파일을 삭제한다.")
    @ApiDocumentResponse
    public void deleteTemp(@RequestParam(name = "fileUrl") String fileUrl) throws TusException, IOException {
        tusFileService.deleteTempUpload(fileUrl);
    }

    @RequestMapping(value = {"upload", "upload/**"}, method = { RequestMethod.POST, RequestMethod.OPTIONS })
    @Operation(summary = "파일 업로드", description = "파일을 업로드 한다.")
    @ApiDocumentResponse
    public void process(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        tusFileUploadService.process(req, res);
    }

    @DeleteMapping(value = {"upload", "upload/**"})
    @Operation(summary = "파일 삭제", description = "업로드한 파일을 삭제한다.")
    @ApiDocumentResponse
    public void delete(final HttpServletRequest req, final HttpServletResponse res) throws IOException, TusException {
        tusFileUploadService.process(req, res);
        String uploadURI = req.getRequestURI();
        tusFileService.deleteTempUpload(uploadURI);
    }

    @RequestMapping(value={"upload", "upload/**"}, method={ RequestMethod.HEAD, RequestMethod.PATCH })
    @Operation(summary = "파일 업로드", description = "파일을 업로드 한다. (최종 파일 업로드 후, fileId를 헤더에 담아서 리턴한다.)")
    @ApiDocumentResponse
    public ResponseEntity<String> upload(final HttpServletRequest req, final HttpServletResponse res) throws IOException, TusException {
        tusFileUploadService.process(req, res);
        String fileId = tusFileService.sendFileId(req);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("X-File-Id", fileId);
        return ResponseEntity.ok().headers(responseHeaders).build();
    }
}