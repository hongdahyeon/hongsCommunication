package hongs.community.hongsCommunity.domain.test;

import hongs.community.hongsCommunity.domain.test.dto.TestDto;
import hongs.community.hongsCommunity.domain.test.dto.TestFileDto;
import hongs.community.hongsCommunity.domain.test.vo.TestVo;
import hongs.community.hongsCommunity.global.hongs.file.tus.HongTusFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;
    private final HongTusFileService hongFileService;

    public List<TestVo> getTestList(){
        return testMapper.testList(new TestDto("test"));
    }

    @Transactional
    public Long save(TestFileDto dto){
        Long fUid = hongFileService.changeSaved(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        testMapper.testSave(dto);
        return dto.getUid();
    }

    @Transactional
    public Long edit(TestFileDto dto){
        Long fUid = hongFileService.changeSaved(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        testMapper.testEdit(dto);
        return dto.getUid();
    }

    public TestVo view(Long uid) {
        return testMapper.view(uid);
    }

}
