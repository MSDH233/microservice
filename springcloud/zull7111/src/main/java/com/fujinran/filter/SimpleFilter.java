package com.fujinran.filter;

import com.fujinran.client.OauthClient;
import com.fujinran.param.OauthCheckParam;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import entities.ResultEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by FuJinRan on 2019/12/21.
 */
@Slf4j
@Component
public class SimpleFilter extends ZuulFilter {

    @Autowired
    private OauthClient oauthClient;

    public static final String  LOGIN_URI = "/api-oauth/sys_user/login";

    public static final String  REGISTER_URI  = "";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1 ;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info("===uri===", request.getRequestURI());
        //注册和登录接口不拦截，其他接口都要拦截校验 token
        if (LOGIN_URI.equals(request.getRequestURI()) ||
                REGISTER_URI.equals(request.getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        ctx.getResponse().setCharacterEncoding("utf-8");
        //这里只做认证 不授权
        //不存在token 则直接转到登陆界面
       String token =  readTokenFromHeader(ctx,request);
       if(token == null ){
           return null ;
       }else {
           String requestURI = request.getRequestURI();
           String[] strs = requestURI.split("/");
           StringBuilder sb = new StringBuilder();
           sb.append("/");
           for(int i=2 ;i <strs.length ; i++){
               sb.append(strs[i]);
               if( i < strs.length-1){
                   sb.append("/");
               }
           }
           verifyToken(ctx, request,token,sb.toString());
       }
       return null ;
    }

    private void setUnauthorizedResponse(RequestContext requestContext, String msg) {
        //终止转发，返回响应报文
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseBody("check false  :" + msg);
    }

    private String readTokenFromHeader(RequestContext requestContext, HttpServletRequest request){
        String headerToken = request.getHeader("token");
        String rquestToken = request.getParameter("token");
        String cookieToken = null ;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    cookieToken = cookie.getValue();
                }
            }
        }
        if (StringUtils.isEmpty(headerToken) && StringUtils.isEmpty(rquestToken) && StringUtils.isEmpty(cookieToken)) {
            setUnauthorizedResponse(requestContext, "未持有令牌");
        }
        Set<String> tokenSet = new HashSet<>();
        if(cookieToken != null ){

            return cookieToken;

        }else if(rquestToken != null ){

                return rquestToken ;
            }
        else if(headerToken != null ){

                return headerToken ;
        }
        return null ;
    }

    private void  verifyToken(RequestContext requestContext, HttpServletRequest request ,String token,String URI) {
        ResultEntity resultEntity = null ;
        HttpServletResponse response = requestContext.getResponse();
        try{
            OauthCheckParam oauthCheckParam = OauthCheckParam.builder().token(token).rquestURI(URI).build();
            resultEntity = oauthClient.checkOauth(oauthCheckParam);
        }
        catch (Exception e){
            log.info(" \n 错误信息: {} ",e.getMessage() );
        }
        if(resultEntity == null ){
            setUnauthorizedResponse(requestContext,"返回消息为空");

        }else {
            String status = resultEntity.getStatus() ;
            if("403".equals(status) || "401".equals(status)){
                log.info("权限不足 {}" ,status);
                setUnauthorizedResponse(requestContext,"权限不足:" + status);
            }
        }

    }
}