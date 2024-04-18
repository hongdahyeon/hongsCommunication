package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.calendar.dto.HongCalendarDto;
import hongs.community.hongsCommunity.domain.calendar.vo.HongCalendarVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongCalendarService {

    private final HongCalendarMapper mapper;

    public List<HongCalendarVo> list(Long userUid){
        return mapper.list(userUid);
    }

    public Long join(HongCalendarDto dto){
        mapper.join(dto);
        return dto.getCalUid();
    }

    public int update(HongCalendarDto dto){
        return mapper.update(dto);
    }

    public int updateDate(HongCalendarDto dto){
        return mapper.updateDate(dto);
    }

    public int delete(Long calUid) {
        return mapper.delete(calUid);
    }
}
