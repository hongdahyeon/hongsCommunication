package hongs.community.hongsCommunity.domain.report;

import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportInsertUpdateDto;
import hongs.community.hongsCommunity.domain.report.dto.HongBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportListVo;
import hongs.community.hongsCommunity.domain.report.vo.HongBoardReportViewVo;
import hongs.community.hongsCommunity.global.hongs.file.common.HongCommonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongBoardReportService {

    private final HongBoardReportMapper boardReportMapper;
    private final HongCommonFileService hongCommonFileService;

    public List<HongBoardReportListVo> list(HongBoardReportListDto dto){
        return boardReportMapper.list(dto);
    }

    public Integer insert(HongBoardReportInsertUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        return boardReportMapper.insert(dto);
    }

    public HongBoardReportViewVo view(Long reportUid) {
        return boardReportMapper.view(reportUid);
    }

    public Integer update(HongBoardReportInsertUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        return boardReportMapper.update(dto);
    }
}
