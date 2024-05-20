package hongs.community.hongsCommunity.domain.report.admin;

import hongs.community.hongsCommunity.domain.report.admin.dto.HongAdminBoardReportInsertUpdateDto;
import hongs.community.hongsCommunity.domain.report.admin.dto.HongAdminBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.admin.vo.HongAdminBoardReportListVo;
import hongs.community.hongsCommunity.domain.report.admin.vo.HongAdminBoardReportViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongAdminBoardReportMapper {

    List<HongAdminBoardReportListVo> list(HongAdminBoardReportListDto dto);

    Integer insert(HongAdminBoardReportInsertUpdateDto dto);

    HongAdminBoardReportViewVo view(Long reportUid);

    Integer update(HongAdminBoardReportInsertUpdateDto dto);

    Integer delete(Long reportUid);
}
