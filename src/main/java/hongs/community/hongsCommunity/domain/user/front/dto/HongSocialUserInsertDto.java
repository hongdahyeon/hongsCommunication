package hongs.community.hongsCommunity.domain.user.front.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongSocialUserInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  소셜 사용자 저장하기 dto
**/

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
