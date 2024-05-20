package hongs.community.hongsCommunity.domain.report;

import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportInsertUpdateDto;
import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportListVo;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongBoardReportMapper {

    List<HongBoardReportListVo> list(HongBoardReportListDto dto);

    Integer insert(HongBoardReportInsertUpdateDto dto);

    HongBoardReportViewVo view(Long reportUid);

    Integer update(HongBoardReportInsertUpdateDto dto);

    Integer delete(Long reportUid);
}
