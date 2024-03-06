package hongs.community.hongsCommunity.global.hongs.file.tus;

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
public class HongTusFileService {

    private final TusFileUploadService uploadService;

    @Transactional
    public String sendFileId(final HttpServletRequest req) throws TusException, IOException {
        String uploadURI = req.getRequestURI();
        UploadInfo info = uploadService.getUploadInfo(uploadURI);
        if(info == null) return null;
        return info.getId().toString();
    }

    @Transactional
    public void deleteTempUpload(String fileUrl) throws TusException, IOException {
        uploadService.deleteUpload(fileUrl);
    }

}