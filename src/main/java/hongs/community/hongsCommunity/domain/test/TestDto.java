package hongs.community.hongsCommunity.domain.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class TestDto {

    private Long uid;
    private String name;

    public TestDto(String name) {
        this.name = name;
    }
}
