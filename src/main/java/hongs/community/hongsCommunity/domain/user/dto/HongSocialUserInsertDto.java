package hongs.community.hongsCommunity.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class HongSocialUserInsertDto {

    private Long socialUserUid;
    private String userId;
    private String userEmail;
    private String userName;

    public HongSocialUserInsertDto(String userId, String userName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }
}
