package hongs.community.hongsCommunity.global.hongs.file.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongDeleteFileDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  임시저장 파일 삭제 dto
**/

@Getter @Setter
@NoArgsConstructor
public class HongDeleteFileDto {

    private String fileUrl;

    public HongDeleteFileDto(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}