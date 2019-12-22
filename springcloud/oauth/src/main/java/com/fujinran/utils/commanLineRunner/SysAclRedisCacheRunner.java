package com.fujinran.utils.commanLineRunner;

import com.fujinran.domain.SysAcl;
import com.fujinran.service.SysAclService;
import com.fujinran.utils.RedisUtils;
import com.fujinran.utils.constant.Constant;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/20.
 */
@Component
@Slf4j
public class SysAclRedisCacheRunner implements CommandLineRunner{

    @Autowired
    private RedisUtils redisUtils ;

    @Autowired
    private SysAclService sysAclService ;

    public static  Cache REDIS_ALLACL_CACHE = null ;

    @Override
    public void run(String... args) throws Exception {

        List<String> allAclStr = (List<String>)redisUtils.get(Constant.REDIS_ALLACL_KEY);

        if(CollectionUtils.isEmpty(allAclStr)){

            allAclStr = new ArrayList<String>();

            List<SysAcl> allAcl = sysAclService.findAllAcl();

            for(SysAcl sysAcl : allAcl){
                allAclStr.add(sysAcl.getUrl());
            }

            redisUtils.set(Constant.REDIS_ALLACL_KEY,allAclStr);
        }
        if(REDIS_ALLACL_CACHE == null ){
            REDIS_ALLACL_CACHE =  CacheBuilder.newBuilder().build();
        }
        REDIS_ALLACL_CACHE.put(Constant.REDIS_ALLACL_KEY,allAclStr);
        log.info(" \n 权限缓存已加载完毕 \n {} " , allAclStr.toString());
    }
}