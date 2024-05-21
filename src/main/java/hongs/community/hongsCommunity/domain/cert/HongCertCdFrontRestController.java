package hongs.community.hongsCommunity.domain.cert;

import hongs.community.hongsCommunity.domain.cert.dto.HongCertCheckDto;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/front/cert")
@Tag(name = "인증 Front RestController", description = "로그인 이전 접근 가능한 RestController")
@RequiredArgsConstructor
public class HongCertCdFrontRestController {

    private final HongCertCdService certCdService;

    @GetMapping("/check-cert.json")
    @Operation(summary = "인증번호 확인하기", description = "사용자의 비밀번호가 만료되어 비밀번호를 연장 혹은 변경한다.")
    @ApiDocumentResponse
    public Response  checkCertCd(HongCertCheckDto dto) {
        Boolean checkedCertCd = certCdService.checkCertCd(dto);
        String message = (checkedCertCd) ? "휴먼 계정이 풀렸습니다." : "가장 최근 받은 인증번호로 인증해주세요. \n 인증번호는 최대 하루까지 사용 가능합니다.";
        return (checkedCertCd) ? Response.ok(message) : Response.badRequest(message);
    }

}
