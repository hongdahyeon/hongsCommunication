package hongs.community.hongsCommunity.domain.test.dto;

import hongs.community.hongsCommunity.global.hongs.dto.FileDto;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName TestFileDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  test_table 파일 같이 저장 dto
**/
@Getter
@Setter
public class TestFileDto extends FileDto {

    private Long fileUid;
    private String name;

}
