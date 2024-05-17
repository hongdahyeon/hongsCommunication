package hongs.community.hongsCommunity.domain.report;

import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongBoardReportMapper {

    List<HongBoardReportListVo> list(HongBoardReportListDto dto);
}
