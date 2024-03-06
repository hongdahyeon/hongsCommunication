package hongs.community.hongsCommunity.global.hongs.file.common;

import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongCommonFileDownloadDto;
import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongSaveFileDto;
import hongs.community.hongsCommunity.global.hongs.file.common.vo.HongFileViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongCommonFileMapper {
    Long generateKey();

    void saveFile(HongSaveFileDto dto);

    void deleteFile(HongDeleteFileDto dto);

    List<HongFileViewVo> list(Long fileUid);

    HongFileViewVo view(String fileUrl);

    void updateDownCnt(HongCommonFileDownloadDto dto);

    void insertLog(HongCommonFileDownloadDto dto);
}