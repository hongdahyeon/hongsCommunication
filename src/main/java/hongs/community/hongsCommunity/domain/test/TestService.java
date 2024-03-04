package hongs.community.hongsCommunity.domain.test;

import hongs.community.hongsCommunity.global.hongs.file.HongFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;
    private final HongFileService hongFileService;

    public List<TestVo> getTestList(){
        return testMapper.testList(new TestDto("test"));
    }

    @Transactional
    public void save(TestFileDto dto){
        Long fUid = hongFileService.changeSaved(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        testMapper.testSave(dto);
    }

}
