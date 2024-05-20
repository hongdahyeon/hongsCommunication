package hongs.community.hongsCommunity.domain.report.admin;

import hongs.community.hongsCommunity.domain.report.admin.dto.HongAdminBoardReportInsertUpdateDto;
import hongs.community.hongsCommunity.domain.report.admin.dto.HongAdminBoardReportListDto;
import hongs.community.hongsCommunity.domain.report.admin.vo.HongAdminBoardReportListVo;
import hongs.community.hongsCommunity.domain.report.admin.vo.HongAdminBoardReportViewVo;
import hongs.community.hongsCommunity.global.hongs.file.common.HongCommonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongAdminBoardReportService {

    private final HongAdminBoardReportMapper adminBoardReportMapper;
    private final HongCommonFileService hongCommonFileService;

    public List<HongAdminBoardReportListVo> list(HongAdminBoardReportListDto dto){
        return adminBoardReportMapper.list(dto);
    }

    public Integer insert(HongAdminBoardReportInsertUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        return adminBoardReportMapper.insert(dto);
    }

    public HongAdminBoardReportViewVo view(Long reportUid) {
        return adminBoardReportMapper.view(reportUid);
    }

    public Integer update(HongAdminBoardReportInsertUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        return adminBoardReportMapper.update(dto);
    }

    public Integer delete(Long reportUid){
        return adminBoardReportMapper.delete(reportUid);
    }
}
