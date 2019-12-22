package com.fujinran.param;

import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by FuJinRan on 2019/12/19.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAclTreeParam implements Serializable{
    private SysRole sysRole ;
    private SysUser sysUser ;
}