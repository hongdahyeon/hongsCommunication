package hongs.community.hongsCommunity.global.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    private static Logger sqlLog = LoggerFactory.getLogger("MYBATIS_SQL_LOG");
    public static final String MYBATIS_SQL_LOG = "mybatis_sql_log";
    public static final String MYBATIS_SQL_STOP_WATCH = "mybatis_sql_stop";

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        req.setAttribute(MYBATIS_SQL_STOP_WATCH, stopWatch);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) throws Exception {
        /* req.getStatus() -> 응답 status : 404, 500, 200 ... */
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) throws Exception {

        if(ex != null) {
            log.info("ex.message: {} ", ex.getMessage());
            return;
        }

        StringBuilder logs = new StringBuilder();
        logs.append("\n========================================");

        String requestURI = req.getRequestURI();
        String method = req.getMethod();
        String url = new UrlPathHelper().getRequestUri(req);

        logs.append("\n# URI: ").append(requestURI);
        logs.append("\n# METHOD: ").append(method);

        StringBuilder sql = (StringBuilder) req.getAttribute(MYBATIS_SQL_LOG);
        if(sql != null) logs.append("\n# SQL: ").append(sql);

        StopWatch stopWatch = (StopWatch) req.getAttribute(MYBATIS_SQL_STOP_WATCH);
        if(stopWatch != null) {
            stopWatch.stop();
            double totalTime = stopWatch.getTotalTimeSeconds();
            logs.append(String.format("\n ==> TIMES: %.2f sec", totalTime));
        }

        logs.append("\n========================================");

        sqlLog.info("{}", logs);
        req.removeAttribute(MYBATIS_SQL_LOG);
        req.removeAttribute(MYBATIS_SQL_STOP_WATCH);
    }

}
