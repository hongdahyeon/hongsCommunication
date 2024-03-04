package hongs.community.hongsCommunity.global.hongs.file;

import hongs.community.hongsCommunity.global.hongs.file.dto.HongDeleteFileDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("api/tus/files")
@RestController
@RequiredArgsConstructor
@Slf4j
public class HongFileRestController {

    private final HongFileService hongFileService;
    private final TusFileUploadService tusFileUploadService;

    @DeleteMapping("temp/delete.json")
    public void deleteTemp(HongDeleteFileDto deleteFileDto) throws TusException, IOException {
        hongFileService.deleteFileReal(deleteFileDto);
    }

    @RequestMapping(value = {"upload", "upload/**"},
                    method = { RequestMethod.POST, RequestMethod.OPTIONS })
    public void process(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        tusFileUploadService.process(req, res);
    }

    @DeleteMapping(value = {"upload", "upload/**"})
    public void delete(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        tusFileUploadService.process(req, res);
        hongFileService.deleteFile(req);
    }


    @RequestMapping(value={"upload", "upload/**"},
                    method={ RequestMethod.HEAD, RequestMethod.PATCH })
    public void upload(final HttpServletRequest req, final HttpServletResponse res) throws IOException, TusException {
        tusFileUploadService.process(req, res);
        hongFileService.saveTempFile(req);
    }
}