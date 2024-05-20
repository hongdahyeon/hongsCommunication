package hongs.community.hongsCommunity.domain.report.user;

import hongs.community.hongsCommunity.domain.report.user.vo.HongBoardReportViewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HongBoardReportService {

    private final HongBoardReportMapper boardReportMapper;

    public HongBoardReportViewVo view(Long reportUid) {
        return boardReportMapper.view(reportUid);
    }
}
