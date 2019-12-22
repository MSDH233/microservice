package com.fujinran.service.impl;

import com.fujinran.domain.SysUser;
import com.fujinran.mapper.SysUserMapper;
import com.fujinran.param.LoginParam;
import com.fujinran.service.SysAclService;
import com.fujinran.service.SysUserService;
import com.fujinran.utils.RedisUtils;
import com.fujinran.utils.bean.ResultEntity;
import com.fujinran.utils.bean.UserInfo;
import com.fujinran.utils.exception.LoginException;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisUtils redisUtils ;

    @Autowired
    private SysAclService sysAclService;

    public List<SysUser> findAllUser(){
        return sysUserMapper.selectAll();
    }

    public SysUser findUser(SysUser user){
        return  sysUserMapper.selectByPrimaryKey(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean  insertUser(SysUser sysUser){
        return sysUserMapper.insertSelective(sysUser)>0?true:false ;
    }
    @Transactional(rollbackFor = Exception.class)
    public boolean  modifyUser(SysUser sysUser){
        return sysUserMapper.updateByPrimaryKeySelective(sysUser)>0?true:false ;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean  deleteUser(SysUser sysUser){
        return sysUserMapper.deleteByPrimaryKey(sysUser)>0?true:false;
    }

    @Override
    // TODO  这里添加邮箱验证码
    public ResultEntity login(LoginParam loginParam) throws Exception {
        if(loginParam == null ){
            return null ;
        }
        String keyword = loginParam.getKeyword() ;
        String password =  loginParam.getPassword() ;
        if(StringUtils.isEmpty(keyword) || StringUtils.isEmpty(password)){
            return null ;
        }
        SysUser foundUser = sysUserMapper.findUserByKeyword(keyword);
        if(foundUser == null ){
            throw new LoginException("该账号不存在");
        }
        if("2".equals(foundUser.getStatus())){
            throw new LoginException("账号已被冻结,请联系管理员解冻");
        }
        //登陆成功
        if(StringUtils.equals(password,foundUser.getPassword())){
            //签发token
            String token = genToken() ;
            //缓存用户
            List<String> userAclUrlList = sysAclService.getAclUrlByUser(foundUser);

            redisUtils.set(token, UserInfo.builder().sysUser(foundUser).userSysList(userAclUrlList).build(),3600);

            UserInfo  userInfo  = (UserInfo) redisUtils.get(token);

            return new ResultEntity(token);
        }
        return null ;
    }

    private String genToken() {

        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz",32) ;

    }

    private String randomCode(String s, int size ) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for( int i =0 ; i < size; i++){
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }

    private String md5(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte [] md5Password = messageDigest.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(md5Password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null ;
    }
}