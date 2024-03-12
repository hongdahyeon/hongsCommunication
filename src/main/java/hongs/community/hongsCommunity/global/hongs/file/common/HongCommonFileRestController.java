package hongs.community.hongsCommunity.global.hongs.file.common;

import hongs.community.hongsCommunity.global.hongs.file.common.vo.HongFileViewVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class HongCommonFileRestController {

    private final HongCommonFileService hongCommonFileService;

    @GetMapping("list.json")
    public Response list(@RequestParam(name = "fileUid") Long fileUid) {
        List<HongFileViewVo> list = hongCommonFileService.list(fileUid);
        return Response.ok(list);
    }

    @GetMapping("download")
    public ResponseEntity<Resource> download(final HttpServletRequest req, final HttpServletResponse res) {
        return hongCommonFileService.download(req);
    }
}
