package hongs.community.hongsCommunity.global.hongs.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import hongs.community.hongsCommunity.global.util.UserUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Aspect
@Order(0)
@Component
public class HongsRequestAspect {

    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void setCreatedBy(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (ObjectUtils.isEmpty(args) || args.length == 0) {
            return;
        }

        setUserUid(args);
    }

    private void setUserUid(Object[] args) {
        Long userUid = 0L;
        if(UserUtil.getLoginUser() != null) userUid = UserUtil.getLoginUserUid();
            for (Object arg : args) {
                setCreated(userUid, arg);
                setUpdated(userUid, arg);
            }
    }

    private void setCreated(Long userUid, Object arg) {
        if (arg instanceof CreateRequest) {
            ((CreateRequest) arg).setRegId(userUid);
            ((CreateRequest) arg).setMdfrId(userUid);
        }
    }

    private void setUpdated(Long userUid, Object arg) {
        if (arg instanceof UpdateRequest) {
            ((UpdateRequest) arg).setMdfrId(userUid);
        }
    }
}
