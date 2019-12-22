package com.fujinran.service.impl;

import com.fujinran.domain.SysDept;
import com.fujinran.dto.DeptLevelDto;
import com.fujinran.mapper.SysDeptMapper;
import com.fujinran.service.SysDeptService;
import com.fujinran.utils.LevelUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper ;

    @Override
    public List<SysDept> findAllDept() {
        return sysDeptMapper.selectAll();
    }

    @Override
    public SysDept findDept(SysDept dept) {
        return sysDeptMapper.selectByPrimaryKey(dept);
    }

    @Override
    public boolean insertDept(SysDept SysDept) throws Exception{
        this.checkDept(SysDept);
        return sysDeptMapper.insertSelective(SysDept)>0?true:false ;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modifyDept(SysDept SysDept) throws Exception {
        //注意此功能不支持由上级部门整体并入下级部门
        this.checkDept(SysDept);
        //更新子部门及当前部门
        this.sysDeptMapper.updateByPrimaryKeySelective(SysDept);
        this.sysDeptMapper.updateChildDept(SysDept);
        return true ;
    }

    @Override
    public boolean deleteDept(SysDept SysDept) {
        return sysDeptMapper.deleteByPrimaryKey(SysDept)>0?true:false ;
    }


    private boolean checkDept(SysDept sysDept) throws Exception{
        //级别是一种标志可以抽象成一个interface 然后把这些方法提取出来
        //顶级部门
        SysDept foundDept = sysDeptMapper.selectByDeptNameAndParentId(sysDept) ;
            if(foundDept != null ){
                throw new Exception("该上级部门下已存在名称相同的部门");
            }
        return true ;
    }


    @Override
    public List<DeptLevelDto> deptTree() {
        List<SysDept> deptList = sysDeptMapper.selectAll();
        List<DeptLevelDto>  deptLevelDtoList = Lists.newArrayList();
        for(SysDept dept : deptList){
            DeptLevelDto deptLevelDto = DeptLevelDto.adapt(dept);
            deptLevelDtoList.add(deptLevelDto);
        }

        return  listToTree(deptLevelDtoList);
    }

    public List<DeptLevelDto> listToTree(List<DeptLevelDto> deptLevelDtoList){
        if(CollectionUtils.isEmpty(deptLevelDtoList)){
            return null ;
        }

        Multimap<String,DeptLevelDto> levelDtoMultimap = ArrayListMultimap.create();

        List<DeptLevelDto> rootList = Lists.newArrayList();
        for(DeptLevelDto dto : deptLevelDtoList){
            levelDtoMultimap.put(dto.getLevel(),dto);
            if(LevelUtils.getRoot().equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        //排序
        Collections.sort(rootList, new DeptComparator());

        transformDeptTree(rootList , LevelUtils.getRoot() , levelDtoMultimap);

        return rootList ;
    }
    /*
    * 用层级的好处是不止可以表示父子关系,所有的后代或者祖先层级都可以表示
    * */
    public void transformDeptTree(List<DeptLevelDto> levelList, String level, Multimap<String, DeptLevelDto> levelDtoMultimap) {
            for(DeptLevelDto deptLevelDto : levelList){
                //获取下一层Level
                String nextLevel = LevelUtils.calculateLevel(level,deptLevelDto.getId());
                //下一层级实例
                List<DeptLevelDto> deptLevelDtoList = (List<DeptLevelDto>)levelDtoMultimap.get(nextLevel);

                //下一层不为空
                if(!CollectionUtils.isEmpty(deptLevelDtoList)){
                    //排序
                    Collections.sort(deptLevelDtoList,new DeptComparator());
                    //添加
                    deptLevelDto.setDeptLevelDtoList(deptLevelDtoList);

                    transformDeptTree(deptLevelDtoList,nextLevel,levelDtoMultimap);
                }
            }
    }


    public  class DeptComparator implements Comparator<DeptLevelDto> {

        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    }
}