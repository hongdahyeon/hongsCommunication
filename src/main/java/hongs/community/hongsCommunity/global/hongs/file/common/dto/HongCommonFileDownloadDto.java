package hongs.community.hongsCommunity.global.hongs.file.common.dto;

import hongs.community.hongsCommunity.global.hongs.dto.Creator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HongCommonFileDownloadDto extends Creator {

    private Long hongFileUid;
    private String fileUrl;

    public HongCommonFileDownloadDto(Long hongFileUid, String fileUrl) {
        this.hongFileUid = hongFileUid;
        this.fileUrl = fileUrl;
    }
}