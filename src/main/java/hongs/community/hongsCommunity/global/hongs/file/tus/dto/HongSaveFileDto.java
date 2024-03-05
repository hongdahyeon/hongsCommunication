package hongs.community.hongsCommunity.global.hongs.file.tus.dto;

import hongs.community.hongsCommunity.global.hongs.dto.Creator;
import hongs.community.hongsCommunity.global.hongs.file.HongFileState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* @fileName HongSaveFileDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  파일 임시저장 dto
**/

@Getter
@NoArgsConstructor
public class HongSaveFileDto extends Creator {

    private Long hongFileUid;
    private String fileName;
    private String fileUrl;
    private String fileId;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private String extension;
    private Integer downCnt;
    private String saved;
    private String deleteYn;

    @Builder(builderMethodName = "insertProgressFile")
    public HongSaveFileDto(Long hongFileUid, String fileName, String fileUrl,String fileId, String filePath, String fileType, Long fileSize, String extension) {
        this.hongFileUid = hongFileUid;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileId = fileId;
        this.filePath = filePath;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.extension = extension;
        this.downCnt = 0;
        this.saved = HongFileState.PROGRESS.toString();
        this.deleteYn = "N";
    }
}