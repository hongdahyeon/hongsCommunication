package hongs.community.hongsCommunity.domain.emotion;

import hongs.community.hongsCommunity.domain.emotion.dto.HongBoardReportEmoDeleteDto;
import hongs.community.hongsCommunity.domain.emotion.dto.HongBoardReportEmoInsertDto;
import hongs.community.hongsCommunity.domain.emotion.dto.HongBoardReportEmoListDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongBoardReportEmoMapper {

    Integer reportEmoCount(HongBoardReportEmoListDto dto);

    Integer insert(HongBoardReportEmoInsertDto dto);

    Integer delete(HongBoardReportEmoDeleteDto dto);
}