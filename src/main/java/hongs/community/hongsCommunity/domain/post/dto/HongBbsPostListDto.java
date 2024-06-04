package hongs.community.hongsCommunity.domain.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongBbsPostListDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 리스트 조회 dto
**/

@Getter @Setter
@NoArgsConstructor
public class HongBbsPostListDto {

    private Long typeUid;
    private String ordering;

    public HongBbsPostListDto(Long typeUid, String ordering) {
        this.typeUid = typeUid;
        this.ordering = ordering;
    }
}
