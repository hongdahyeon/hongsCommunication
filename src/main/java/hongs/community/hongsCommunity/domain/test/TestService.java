package hongs.community.hongsCommunity.domain.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    public List<TestVo> getTestList(){
        return testMapper.testList(new TestDto("test"));
    }

}
