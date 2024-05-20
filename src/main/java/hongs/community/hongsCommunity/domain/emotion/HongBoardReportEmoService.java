package hongs.community.hongsCommunity.domain.emotion;

import hongs.community.hongsCommunity.domain.emotion.dto.HongBoardReportEmoDeleteDto;
import hongs.community.hongsCommunity.domain.emotion.dto.HongBoardReportEmoInsertDto;
import hongs.community.hongsCommunity.domain.emotion.dto.HongBoardReportEmoListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HongBoardReportEmoService {

    private final HongBoardReportEmoMapper reportEmoMapper;

    public Boolean reportEmoTF(HongBoardReportEmoListDto dto) {
        Integer emoCount = reportEmoMapper.reportEmoCount(dto);
        return (emoCount == 1);
    }

    public Integer reportEmoCount(HongBoardReportEmoListDto dto) {
        return reportEmoMapper.reportEmoCount(dto);
    }

    public Integer insert(HongBoardReportEmoInsertDto dto){
        Integer insert = reportEmoMapper.insert(dto);
        Integer emoCount = this.reportEmoCount(new HongBoardReportEmoListDto(dto.getBoardReportUid(), dto.getEmotionCode(), "false"));
        return (insert == 1) ? emoCount : (emoCount - 1) ;
    }

    public Integer delete(HongBoardReportEmoDeleteDto dto) {
        Integer delete = reportEmoMapper.delete(dto);
        Integer emoCount = this.reportEmoCount(new HongBoardReportEmoListDto(dto.getBoardReportUid(), dto.getEmotionCode(), "false"));
        return (delete == 1) ? emoCount : (emoCount + 1) ;
    }
}
