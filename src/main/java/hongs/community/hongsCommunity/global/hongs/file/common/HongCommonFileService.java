package hongs.community.hongsCommunity.global.hongs.file.common;

import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongCommonFileDownloadDto;
import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongSaveFileDto;
import hongs.community.hongsCommunity.global.hongs.file.common.vo.HongFileViewVo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${hong.tus.file.root}")
    private String tusStoragePath;

    private Long generateKey(){
        return fileMapper.generateKey();
    }

    @Transactional(readOnly = true)
    public List<HongFileViewVo> list(Long fileUid){
        return fileMapper.list(fileUid);
    }

    public ResponseEntity<Resource> download(HttpServletRequest req) {
        String fileUrl = req.getParameter("fileUrl");

        if(!"null".equals(fileUrl)) {
            HongFileViewVo view = this.view(fileUrl);

            String download = req.getParameter("download");
            if ("Y".equals(download)) this.updateDownCntAndLog(view.getHongFileUid(), fileUrl);

            File targetFile = new File(String.format("%s%s%s%s%s%s%s", tusStoragePath, File.separator, "uploads", File.separator, view.getFileId(), File.separator, "data"));
            Resource resource = new FileSystemResource(targetFile.getPath());

            String fileName = "";
            try {
                fileName = URLEncoder.encode(view.getFileName(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
            }

            String disposition = "attachment;filename=" + fileName + ";";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header("Content-Disposition", disposition)
                    .body(resource);
        }
        return null;
    }

    public HongFileViewVo view(String fileUrl) {
        return fileMapper.view(fileUrl);
    }

    @Transactional
    public void updateDownCntAndLog(Long fileUid, String fileUrl) {
        fileMapper.updateDownCnt(new HongCommonFileDownloadDto(fileUid, fileUrl));
        fileMapper.insertLog(new HongCommonFileDownloadDto(fileUid, fileUrl));
    }

    @Transactional
    public Long saveAndDelFiles(Long uid, HongSaveFileDto[] addFile, String[] delFile){
        Long fileUid = null;
        if((addFile != null && addFile.length > 0) || (delFile != null && delFile.length > 0)) {
            fileUid = (uid == null) ? generateKey() : uid;
            for (HongSaveFileDto dto : addFile){
                dto.setHongFileUid(fileUid);
                fileMapper.saveFile(dto);
            }

            for (String delFileUrl : delFile){
                fileMapper.deleteFile(new HongDeleteFileDto(delFileUrl));
            }
        }
        return fileUid;
    }
}
