package hongs.community.hongsCommunity.global.hongs.file.common;

import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongCommonFileDownloadDto;
import hongs.community.hongsCommunity.global.hongs.file.common.vo.HongFileViewVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HongCommonFileService {

    private final HongCommonFileMapper fileMapper;
    @Transactional(readOnly = true)
    public List<HongFileViewVo> list(Long fileUid){
        return fileMapper.list(fileUid);
    }

    public ResponseEntity<Resource> download(HttpServletRequest req) {
        String fileUrl = req.getParameter("fileUrl");
        log.info("fileUrl : {} ", fileUrl);

        HongFileViewVo view = this.view(fileUrl);

        String download = req.getParameter("download");
        if("Y".equals(download)) updateDownCntAndLog(view.getHongFileUid(), fileUrl);

        Resource resource = new FileSystemResource(view.getFilePath());

        String fileName = "";
        try{
            fileName = URLEncoder.encode(view.getFileName(), "UTF-8");
        } catch(UnsupportedEncodingException e) {}

        String disposition = "attachment;filename=" + fileName + ";";

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", disposition)
                .body(resource);
    }

    public HongFileViewVo view(String fileUrl) {
        return fileMapper.view(fileUrl);
    }

    @Transactional
    private void updateDownCntAndLog(Long fileUid, String fileUrl) {
        fileMapper.updateDownCnt(new HongCommonFileDownloadDto(fileUid, fileUrl));
        fileMapper.insertLog(new HongCommonFileDownloadDto(fileUid, fileUrl));
    }
}