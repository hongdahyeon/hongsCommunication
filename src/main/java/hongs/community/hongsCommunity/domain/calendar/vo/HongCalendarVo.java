package hongs.community.hongsCommunity.domain.calendar.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
public class HongCalendarVo {

    private Long uid;
    private Long groupId;
    private String title;
    private String start;
    private String end;
    private String backgroundColor;
    private String textColor;
    private boolean allDay;
}