package hongs.community.hongsCommunity.global.hongs.file.common.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongFileViewVo {

    private Long hongFileUid;
    private String fileUrl;
    private String fileId;
    private String fileName;
    private String fileType;
    private Long fileSize;
    private String extension;
    private Integer downCnt;
    private String saved;
    private String regDt;

}
