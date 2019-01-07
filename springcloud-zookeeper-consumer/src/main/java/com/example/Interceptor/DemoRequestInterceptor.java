package com.example.Interceptor;

import com.google.common.base.Strings;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

/**
 * 自定义请求拦截器，可以用于添加认证Token等操作
 *
 * @author Raven
 */
public class DemoRequestInterceptor implements RequestInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoRequestInterceptor.class);

    @Override
    public void apply(final RequestTemplate requestTemplate) {
//        HttpServletRequest request = this.getCurrentRequest();
//        this.logRequest(request);
//        requestTemplate.header(HttpHeaders.USER_AGENT, "cjyun-demo-client/1.0");
//        String auth = this.getAuth(request);
//        if (!Strings.isNullOrEmpty(auth)) {
//            LOGGER.warn("Authorization: {}", auth);
//            requestTemplate.header(HttpHeaders.AUTHORIZATION, "service CJTLIS:87DDBAA21F7F33CC81719A776AD8991F",auth);
//}
    }

    private String getAuth(final HttpServletRequest request) {
        String auth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Strings.isNullOrEmpty(auth)) {

            auth = Stream.of(request.getCookies())
                    .filter(c -> "token".equalsIgnoreCase(c.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);

            if (!Strings.isNullOrEmpty(auth)) {
                auth = "token " + auth;
            }
        }

        return auth;
    }

    private void logRequest(final HttpServletRequest request) {
        String ua = request.getHeader(HttpHeaders.USER_AGENT);
        String ip = request.getRemoteAddr();
        LOGGER.warn("RemoteAddress: {}, UserAgent: {}", ip, ua);
    }

    private HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

}
