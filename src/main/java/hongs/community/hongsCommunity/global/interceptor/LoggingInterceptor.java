package hongs.community.hongsCommunity.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    private static Logger sqlLog = LoggerFactory.getLogger("MYBATIS_SQL_LOG");
    public static final String MYBATIS_SQL_LOG = "mybatis_sql_log";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
        /* req.getStatus() -> 응답 status : 404, 500, 200 ... */
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {

        // TODO: 너무 많이 찍히는 로그 원인 찾아야함
        StringBuilder logs = new StringBuilder();
        logs.append("\n========================================");

        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        String url = new UrlPathHelper().getRequestUri(req);

        logs.append("\nURI: ").append(requestURI);
        logs.append("\nMETHOD: ").append(method);
        logs.append("\nURL: ").append(url);

        StringBuilder sql = (StringBuilder) req.getAttribute(MYBATIS_SQL_LOG);
        if(sql != null) logs.append("\nsql: ").append(sql);

        logs.append("\n========================================");

        sqlLog.info("{}", logs);
        req.removeAttribute(MYBATIS_SQL_LOG);
    }
}
