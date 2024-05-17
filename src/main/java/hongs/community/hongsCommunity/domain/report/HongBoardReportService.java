package hongs.community.hongsCommunity.domain.report;

import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongBoardReportService {

    private final HongBoardReportMapper boardReportMapper;

    public List<HongBoardReportListVo> list(HongBoardReportListDto dto){
        return boardReportMapper.list(dto);
    }
}
