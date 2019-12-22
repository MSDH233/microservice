package com.fujinran.dto;

import com.fujinran.domain.SysDept;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeptLevelDto extends SysDept {
       private List<DeptLevelDto> deptLevelDtoList = Lists.newArrayList();
       public static DeptLevelDto adapt(SysDept sysDept){
            DeptLevelDto dto = new DeptLevelDto();
           try {
               BeanUtils.copyProperties(dto,sysDept);
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           }
           return dto ;
       }
}