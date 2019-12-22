package com.fujinran.utils.bean;

import com.fujinran.domain.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/20.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo  implements Serializable {
    private SysUser sysUser;
    private List<String>  userSysList ;
}