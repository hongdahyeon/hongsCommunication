package hongs.community.hongsCommunity.global.hongs.dto.request;

import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongSaveFileDto;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName FileRequest
* @author dahyeon
* @version 1.0.0
* @date 2024-03-05
* @summary  파일 관련 add, del 파일 리스트 dto (+) 생성자, 수정자 dto
**/

@Getter @Setter
public class FileRequest extends CreateRequest {

    protected HongSaveFileDto[] addFile;
    protected String[] delFile;
}