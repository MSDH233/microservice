package com.fujinran.dto;

import com.fujinran.domain.SysAcl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by FuJinRan on 2019/12/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAclDto  extends SysAcl implements Serializable{

    //是否由权限分配
    private boolean hasAcl = false  ;
    //是否默认选中
    private boolean checked  = false ;

    public static SysAclDto adapt(SysAcl sysAcl){

        if(sysAcl != null){
            try {
                SysAclDto sysAclDto= new SysAclDto();
                BeanUtils.copyProperties(sysAclDto,sysAcl);
                return sysAclDto;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null ;
    }

}