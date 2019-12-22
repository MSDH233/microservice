package com.fujinran.utils.filter;

import com.fujinran.domain.SysAcl;
import com.fujinran.param.OauthCheckParam;
import com.fujinran.utils.ApplicationConetextHelper;
import com.fujinran.utils.RedisUtils;
import com.fujinran.utils.bean.ResultEntity;
import com.fujinran.utils.bean.UserInfo;
import com.fujinran.utils.commanLineRunner.SysAclRedisCacheRunner;
import com.fujinran.utils.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/20.
 */
@Component
public class TokenFilter  {


    public ResultEntity check(OauthCheckParam oauthCheckParam){
        String token = null ;
        String rquestURI = null ;
        if(oauthCheckParam == null || (token=oauthCheckParam.getRquestURI()) == null  || (rquestURI=oauthCheckParam.getToken())== null ){
            return ResultEntity.builder().message("参数为空 校验失败").build();
        }
        //如果是登陆请求或者 不再权限列表之内 那么默认放行
        if(rquestURI.startsWith("/sys_user/login")){
           return ResultEntity.builder().message("未配置该路径权限").flag(true).status("200").data(oauthCheckParam).build();
        }
        List<String> REDIS_ALLACL_CACHE = (List<String>) SysAclRedisCacheRunner.REDIS_ALLACL_CACHE.getIfPresent(Constant.REDIS_ALLACL_KEY);
        boolean isContains = false ;
        for(String aclCache : REDIS_ALLACL_CACHE){
            if(aclCache.equals(rquestURI)){
                isContains = true ;
                break;
            }
        }
        if(!isContains){
            return new ResultEntity(true);
        } else{
            //未传入签发的Token
            if (StringUtils.isEmpty(token)) {
                return ResultEntity.builder().token(token).message("用户未登陆").status("401").build();
            }else{
                //传入则行权限校验
                RedisUtils redisUtils = ApplicationConetextHelper.popBean(RedisUtils.class);
                //从redis中查找token , 将Token转换未UserInfo
                UserInfo userInfo = (UserInfo) redisUtils.get(token);
                //校验是否有权限
                if(userInfo.getUserSysList().contains(SysAcl.builder().url(rquestURI).build())){
                    //如果有则继续
                    return new ResultEntity(true);
                }else{
                    //没有则返回
                    return ResultEntity.builder().token(token).message("用户权限不足,请联系管理员").status("403").data(userInfo).build();

            }
            }
        }
    }

}