package hongs.community.hongsCommunity.domain.team.team;

import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HongTeamService {

    private final HongTeamMapper teamMapper;

    public List<HongTeamListVo> list() {
        return teamMapper.list();
    }
}
