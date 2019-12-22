package com.fujinran.dto;

import com.fujinran.domain.SysAclModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAclModuleDto extends SysAclModule {

    private List<SysAclModuleDto> sysAclModuleDtoList ;

    private List<SysAclDto> sysAclDtoList ;

    public static SysAclModuleDto adapt(SysAclModule sysAclModule){
        SysAclModuleDto dto = new SysAclModuleDto();
        try {
            BeanUtils.copyProperties(dto,sysAclModule);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return dto ;
    }
}