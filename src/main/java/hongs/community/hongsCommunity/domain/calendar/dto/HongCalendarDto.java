package hongs.community.hongsCommunity.domain.calendar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongCalendarDto {

    private Long calUid;
    private String title;
    private String start;
    private String end;
    private String backgroundColor;
    private String borderColor;
    private String textColor;
    private boolean allDay;
    private Long userUid;
}