package hongs.community.hongsCommunity.domain.report.user;

import hongs.community.hongsCommunity.domain.report.user.vo.HongBoardReportViewVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongBoardReportMapper {

    HongBoardReportViewVo view(Long reportUid);
}
