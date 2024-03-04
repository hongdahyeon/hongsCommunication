package hongs.community.hongsCommunity.global.hongs.file;

import hongs.community.hongsCommunity.global.hongs.file.dto.HongChangeStateFileDto;
import hongs.community.hongsCommunity.global.hongs.file.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.dto.HongSaveFileDto;
import hongs.community.hongsCommunity.global.util.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class HongFileService {

    private final TusFileUploadService uploadService;
    private final HongFileMapper fileMapper;

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

        HongSaveFileDto fileDto = HongSaveFileDto.insertProgressFile()
                .hongFileUid(fileUid)
                .fileName(info.getFileName())
                .fileUrl(uploadURI)
                .fileType(info.getFileMimeType())
                .fileSize(info.getLength())
                .extension(FileUtil.extension(info.getFileName(), false))
                .build();

        fileMapper.insertTempFile(fileDto);
    }

    public void deleteFile(final HttpServletRequest req) {
        String uploadURI = req.getRequestURI();
        fileMapper.deleteFileReal(new HongDeleteFileDto(uploadURI));
    }


    public void deleteFileReal(HongDeleteFileDto deleteFileDto) throws TusException, IOException {
        uploadService.deleteUpload(deleteFileDto.getFileUrl());
        fileMapper.deleteFileReal(deleteFileDto);
    }

    @Transactional
    public Long changeSaved(Long uid, String[] addFile, String[] delFile){
        Long fileUid = (uid == null) ? generateKey() : uid;

        for(String addFileUrl : addFile){
            fileMapper.changeFileState(new HongChangeStateFileDto(fileUid, addFileUrl, HongFileState.SAVED));
        }

        for(String delFileUrl : delFile) {
            fileMapper.changeFileState(new HongChangeStateFileDto(fileUid, delFileUrl, HongFileState.DELETED));
        }

        return fileUid;
    }

}