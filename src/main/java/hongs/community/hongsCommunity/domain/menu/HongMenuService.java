package hongs.community.hongsCommunity.domain.menu;

import hongs.community.hongsCommunity.domain.menu.dto.HongMenuDto;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongMenuService {

    private final HongMenuMapper mapper;
    public List<HongMenuVo> list(String menuRole) {
        List<HongMenuVo> parentList = mapper.parentList(new HongMenuDto(menuRole, "Y", "N"));
        return parentList.stream().map(hongMenuVo -> {
            List<HongMenuVo> childList = mapper.childList(new HongMenuDto(hongMenuVo.getMenuUid(), "Y", "N"));
            hongMenuVo.setChildren(childList);
            return hongMenuVo;
        }).toList();
    }

}
