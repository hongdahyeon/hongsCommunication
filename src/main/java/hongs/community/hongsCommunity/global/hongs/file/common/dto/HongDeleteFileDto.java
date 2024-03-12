package hongs.community.hongsCommunity.global.hongs.file.common.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import hongs.community.hongsCommunity.global.hongs.file.HongFileState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongDeleteFileDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  저장된 파일 삭제 -> delete_yn , saved 상태 변경
**/

@Getter @Setter
@NoArgsConstructor
public class HongDeleteFileDto extends CreateRequest {

    private String fileUrl;
    private String deleteYn;
    private String saved;

    public HongDeleteFileDto(String fileUrl) {
        this.fileUrl = fileUrl;
        this.deleteYn = "Y";
        this.saved = HongFileState.DELETED.toString();
    }
}