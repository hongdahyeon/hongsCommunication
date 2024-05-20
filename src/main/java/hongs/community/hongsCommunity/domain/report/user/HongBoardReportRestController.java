package hongs.community.hongsCommunity.domain.report.user;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/board/report")
@RequiredArgsConstructor
@Tag(name = "USER 권한 > 게시판 유형별 게시글 보기", description = "USER 권한에서 접근이 가능하며, 게시판 유형별 게시글을 관람한다.")
public class HongBoardReportRestController {

    private final HongBoardReportService boardReportService;
}
