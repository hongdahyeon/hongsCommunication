package hongs.community.hongsCommunity.global.hongs.file.dto;

import hongs.community.hongsCommunity.global.hongs.dto.Creator;
import hongs.community.hongsCommunity.global.hongs.file.HongFileState;
import lombok.Getter;
import lombok.NoArgsConstructor;

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