package hongs.community.hongsCommunity.global.hongs.file.common;

import hongs.community.hongsCommunity.global.hongs.file.common.dto.HongCommonFileDownloadDto;
import hongs.community.hongsCommunity.global.hongs.file.common.vo.HongFileViewVo;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongChangeStateFileDto;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongSaveFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongCommonFileMapper {
    List<HongFileViewVo> list(Long fileUid);

    HongFileViewVo view(String fileUrl);

    void updateDownCnt(HongCommonFileDownloadDto dto);

    void insertLog(HongCommonFileDownloadDto dto);
}