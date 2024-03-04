package hongs.community.hongsCommunity.domain.test;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestRestController {

    private final TestService testService;

    @PostMapping("/save.json")
    public int save(@RequestBody TestFileDto dto) {
        testService.save(dto);
        return 1;
    }
}
