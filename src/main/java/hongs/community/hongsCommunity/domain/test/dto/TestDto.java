package hongs.community.hongsCommunity.domain.test.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName TestDto
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  test_table 저장 dto
**/

@Getter @Setter
@NoArgsConstructor
public class TestDto {

    private Long uid;
    private String name;

    public TestDto(String name) {
        this.name = name;
    }
}
