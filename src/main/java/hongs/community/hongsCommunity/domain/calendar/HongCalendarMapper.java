package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.calendar.dto.HongCalendarDto;
import hongs.community.hongsCommunity.domain.calendar.vo.HongCalendarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongCalendarMapper {

    List<HongCalendarVo> list(Long userUid);

    void join(HongCalendarDto dto);

    int update(HongCalendarDto dto);

    int updateDate(HongCalendarDto dto);

    int delete(Long calUid);
}
