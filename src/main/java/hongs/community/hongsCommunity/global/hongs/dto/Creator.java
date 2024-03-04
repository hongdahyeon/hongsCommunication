package hongs.community.hongsCommunity.global.hongs.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Creator {

    private Long regId;
    private String regDt;

    private Long mdfrId;
    private String mdfrDt;

    public Creator() {
        // TODO : 추후 로그인 이후에는 로그인 유저 정보 가져오기
        this.regId = 1L;
        this.mdfrId = 1L;
    }
}