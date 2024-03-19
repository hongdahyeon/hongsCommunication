package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.calendar.vo.HongCalendarVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface HongCalendarMapper {

    List<HongCalendarVo> list(Long userUid);
}
