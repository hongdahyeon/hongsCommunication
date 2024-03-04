package hongs.community.hongsCommunity.global.hongs.file.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class HongDeleteFileDto {

    private String fileUrl;

    public HongDeleteFileDto(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}