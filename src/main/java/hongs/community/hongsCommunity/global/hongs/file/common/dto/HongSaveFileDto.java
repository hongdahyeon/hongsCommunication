package hongs.community.hongsCommunity.global.hongs.file.common.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongSaveFileDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  첨부파일 저장 dto
**/

@Getter @Setter
public class HongSaveFileDto extends CreateRequest {

    private Long hongFileUid;
    private String fileNm;
    private String fileUrl;
    private String fileId;
    private String fileType;
    private Long fileSize;
    private String extension;
    private Integer downCnt;
    private String saved;
    private String deleteYn;

    public void setRegMdfr(Long userUid) {
        this.regId = userUid;
        this.mdfrId = userUid;
    }

}