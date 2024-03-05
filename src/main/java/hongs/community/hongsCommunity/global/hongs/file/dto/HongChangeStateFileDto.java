package hongs.community.hongsCommunity.global.hongs.file.dto;

import hongs.community.hongsCommunity.global.hongs.dto.Creator;
import hongs.community.hongsCommunity.global.hongs.file.HongFileState;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* @fileName HongChangeStateFileDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  임시저장된 파일들 최종 저장 및 파일 삭제 dto
**/

@Getter
@NoArgsConstructor
public class HongChangeStateFileDto extends Creator {

    private Long hongFileUid;
    private String fileUrl;
    private String saved;
    private String deleteYn;

    public HongChangeStateFileDto(Long hongFileUid, String fileUrl, HongFileState saved) {
        this.hongFileUid = hongFileUid;
        this.fileUrl = fileUrl;
        this.saved = saved.toString();
        if(saved.equals(HongFileState.DELETED)) {
            this.deleteYn = "Y";
        }
    }
}