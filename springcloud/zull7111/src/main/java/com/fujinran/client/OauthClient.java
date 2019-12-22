package com.fujinran.client;

import com.fujinran.param.OauthCheckParam;
import entities.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by FuJinRan on 2019/12/21.
 */
@FeignClient("microservice-oauth")
public interface OauthClient {

    @RequestMapping(value = "sys_acl/checkAcl",method = RequestMethod.POST)
    public ResultEntity checkOauth(@RequestBody  OauthCheckParam oauthCheckParam);
}