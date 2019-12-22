package com.fujinran.service;

import com.fujinran.domain.SysUser;
import com.fujinran.param.LoginParam;
import com.fujinran.utils.bean.ResultEntity;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
public interface SysUserService {
    public List<SysUser> findAllUser();
    public SysUser findUser(SysUser sysUser);
    public boolean  insertUser(SysUser sysUser);
    public boolean  modifyUser(SysUser sysUser);
    public boolean  deleteUser(SysUser sysUser);
    public ResultEntity login(LoginParam keyword) throws Exception;
}