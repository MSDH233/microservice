package com.fujinran.web.interceptor;

import com.fujinran.utils.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Slf4j
public class HttpInterceptor extends  HandlerInterceptorAdapter{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI().toString();
        Map map = new HashMap(request.getParameterMap());
        long startTime = System.currentTimeMillis();
        map.put("startTime",startTime);
        log.info("requst start. url:{} , params:{} " ,url , JsonFormat.format(map,"yyyy-MM-dd"));
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        String url = request.getRequestURI().toString();

        Map map = new HashMap(request.getParameterMap());

        log.info("requst finished. url:{} , params:{} " ,url , JsonFormat.format(map,"yyyy-MM-dd"));
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {

        String url = request.getRequestURI().toString();
        Map map = new HashMap(request.getParameterMap());
        long endTime = System.currentTimeMillis();
        map.put("endTime",endTime);
        log.info("requst completed. url:{} , params:{} " ,url , JsonFormat.format(map,"yyyy-MM-dd"));
    }

}