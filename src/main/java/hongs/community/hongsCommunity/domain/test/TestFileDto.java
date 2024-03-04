package hongs.community.hongsCommunity.domain.test;

import hongs.community.hongsCommunity.global.hongs.dto.FileDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestFileDto extends FileDto {

    private Long fileUid;
    private String name;

}
