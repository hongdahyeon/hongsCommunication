package hongs.community.hongsCommunity.domain.code.dto;


import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
* @fileName HongCodeChildMergeDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-10
* @summary 자식 코드 수정 / 추가 / 삭제
**/

@Getter @Setter
public class HongCodeChildMergeDto extends CreateRequest {

    private String upperCodeVal;
    private List<HongCodeChildDto> insertChild = new ArrayList<>();
    private List<HongCodeChildDto> updateChild = new ArrayList<>();
    private List<Long> delUids = new ArrayList<>();
}
