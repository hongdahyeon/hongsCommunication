package hongs.community.hongsCommunity.global.hongs.file.tus;

import hongs.community.hongsCommunity.global.hongs.file.HongFileState;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongChangeStateFileDto;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongSaveFileDto;
import hongs.community.hongsCommunity.global.util.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HongTusFileService {

    private final TusFileUploadService uploadService;
    private final HongTusFileMapper fileMapper;

    @Value("${hong.tus.file.download}")
    private String tusFileDownloadPath;

    private Long generateKey(){
        return fileMapper.generateKey();
    }

    @Transactional
    public void saveTempFile(final HttpServletRequest req) throws TusException, IOException {
        String uploadURI = req.getRequestURI();
        UploadInfo info = uploadService.getUploadInfo(uploadURI);

        if(info == null) return;

        String uid = info.getMetadata().get("uid");
        Long fileUid = ( uid != null ) ? Long.parseLong(uid) :  0L;

        File targetFile = new File(String.format("%s%s%s%s%s", tusFileDownloadPath, File.separator, info.getId(), File.separator, "data"));

        HongSaveFileDto fileDto = HongSaveFileDto.insertProgressFile()
                .hongFileUid(fileUid)
                .fileName(info.getFileName())
                .fileUrl(uploadURI)
                .fileId(info.getId().toString())
                .filePath(targetFile.getAbsolutePath())
                .fileType(info.getFileMimeType())
                .fileSize(info.getLength())
                .extension(FileUtil.extension(info.getFileName(), false))
                .build();

        fileMapper.insertTempFile(fileDto);
    }

    @Transactional
    public void deleteFile(final HttpServletRequest req) {
        String uploadURI = req.getRequestURI();
        fileMapper.deleteFileReal(new HongDeleteFileDto(uploadURI));
    }


    @Transactional
    public void deleteFileReal(HongDeleteFileDto deleteFileDto) throws TusException, IOException {
        uploadService.deleteUpload(deleteFileDto.getFileUrl());
        fileMapper.deleteFileReal(deleteFileDto);
    }

    @Transactional
    public Long changeSaved(Long uid, String[] addFile, String[] delFile){
        Long fileUid = null;
        if((addFile != null && addFile.length > 0) || (delFile != null && delFile.length > 0)) {
            fileUid = (uid == null) ? generateKey() : uid;
            for (String addFileUrl : addFile)
                fileMapper.changeFileState(new HongChangeStateFileDto(fileUid, addFileUrl, HongFileState.SAVED));
            for (String delFileUrl : delFile)
                fileMapper.changeFileState(new HongChangeStateFileDto(fileUid, delFileUrl, HongFileState.DELETED));
        }
        return fileUid;
    }

}