package hongs.community.hongsCommunity.global.hongs.file.common.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HongCommonFileDownloadDto extends CreateRequest {

    private Long hongFileUid;
    private String fileUrl;

    public HongCommonFileDownloadDto(Long hongFileUid, String fileUrl) {
        this.hongFileUid = hongFileUid;
        this.fileUrl = fileUrl;
        this.regId = UserUtil.getLoginUserUid();
    }
}