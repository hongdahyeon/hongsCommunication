package hongs.community.hongsCommunity.global.hongs.file.tus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/tus/files")
@RestController
@RequiredArgsConstructor
@Slf4j
public class HongTusFileRestController {

    private final HongTusFileService tusFileService;
    private final TusFileUploadService tusFileUploadService;

    @DeleteMapping("temp/delete.json")
    public void deleteTemp(@RequestParam(name = "fileUrl") String fileUrl) throws TusException, IOException {
        tusFileService.deleteTempUpload(fileUrl);
    }

    @RequestMapping(value = {"upload", "upload/**"}, method = { RequestMethod.POST, RequestMethod.OPTIONS })
    public void process(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        tusFileUploadService.process(req, res);
    }

    @DeleteMapping(value = {"upload", "upload/**"})
    public void delete(final HttpServletRequest req, final HttpServletResponse res) throws IOException, TusException {
        tusFileUploadService.process(req, res);
        String uploadURI = req.getRequestURI();
        tusFileService.deleteTempUpload(uploadURI);
    }

    @RequestMapping(value={"upload", "upload/**"}, method={ RequestMethod.HEAD, RequestMethod.PATCH })
    public ResponseEntity<String> upload(final HttpServletRequest req, final HttpServletResponse res) throws IOException, TusException {
        tusFileUploadService.process(req, res);
        String fileId = tusFileService.sendFileId(req);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("X-File-Id", fileId);
        return ResponseEntity.ok().headers(responseHeaders).build();
    }
}