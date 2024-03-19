package hongs.community.hongsCommunity.domain.calendar;

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
}
