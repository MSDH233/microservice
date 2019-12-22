package com.fujinran.service.impl;

import com.fujinran.domain.SysAcl;
import com.fujinran.domain.SysAclModule;
import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import com.fujinran.dto.SysAclDto;
import com.fujinran.dto.SysAclModuleDto;
import com.fujinran.mapper.SysAclModuleMapper;
import com.fujinran.service.SysAclModuleService;
import com.fujinran.service.SysAclService;
import com.fujinran.utils.LevelUtils;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@Service
public class SysAclModuleServiceImpl implements SysAclModuleService {

    @Autowired
    private SysAclModuleMapper sysAclModuleMapper ;

    @Autowired
    private SysAclService sysAclService;

    @Override
    public List<SysAclModule> findAllAclModule() {
        return null;
    }

    @Override
    public SysAclModule findAclModule(SysAclModule sysAclModule) {
        return null;
    }

    @Override
    public boolean insertAclModule(SysAclModule sysAclModule) throws Exception{
        this.checkAclModule(sysAclModule);
        return sysAclModuleMapper.insertSelective(sysAclModule)>0?true:false ;
    }

    @Override
    public boolean modifyAclModule(SysAclModule sysAclModule) {
        return false;
    }

    @Override
    public boolean deleteAclModule(SysAclModule sysAclModule) {
        return false;
    }

    private boolean checkAclModule(SysAclModule sysAclModule) throws Exception{
        //级别是一种标志可以抽象成一个interface 然后把这些方法提取出来
        //顶级部门
        SysAclModule foundSysAclModule = sysAclModuleMapper.selectAclModuleByNameAndParentId(sysAclModule) ;
        if(foundSysAclModule != null ){
            throw new Exception("该模块下已存在名称相同的模块");
        }
        return true ;
    }

    @Override
    //获取当前用户的权限树
    public List<SysAclModuleDto> currentUserAclModuleTree(SysUser su) {

        List<SysAclModule> sysAclModuleList = sysAclModuleMapper.selectAll();

        List<SysAclModuleDto>  sysAclModuleDtoList = Lists.newArrayList();
        for(SysAclModule sysAclModule : sysAclModuleList){
            SysAclModuleDto sysAclModuleDto = SysAclModuleDto.adapt(sysAclModule);
            sysAclModuleDtoList.add(sysAclModuleDto);
        }
        //不传ROlE 默认获取当前用户的所有权限
        this.bindAclWithAclModule(su,sysAclModuleDtoList,null);

        //构建权限模块树
        return  listToTree(sysAclModuleDtoList);
    }

    @Override
    //获取角色的权限树,方便对权限进行操作
    public List<SysAclModuleDto> roleAclModuleTree(SysUser su , SysRole sr) {
        List<SysAclModule> sysAclModuleList = sysAclModuleMapper.selectAll();
        List<SysAclModuleDto>  sysAclModuleDtoList = Lists.newArrayList();
        for(SysAclModule sysAclModule : sysAclModuleList){
            SysAclModuleDto sysAclModuleDto = SysAclModuleDto.adapt(sysAclModule);
            sysAclModuleDtoList.add(sysAclModuleDto);
        }
        this.bindAclWithAclModule(su,sysAclModuleDtoList,sr);
        return listToTree(sysAclModuleDtoList);
    }

    //为权限模块设置权限点 为系统的全部权限点 并校验是否能选中 是否有权限
    public  List<SysAclModuleDto> bindAclWithAclModule(SysUser su , List<SysAclModuleDto> sysAclModuleDtoList ,SysRole sysRole){
        if(su != null ){
            //获取系统所有权限
            List<SysAcl> sysAllAclList =  sysAclService.findAllAcl();
            //在这里要判断是否为Admin 设置权限点能否被选中或是否有权限
            //获取用户所有权限
            List<SysAcl> userSysAclList = this.getAclByUser(su);
            //获取角色所有权限

            List<SysAcl> roleSysAclList = sysAclService.getAclByRole(sysRole);

            //是否超级用户
            boolean isAdmin = this.isAdmin(su);

            this.setAclForAclModule(userSysAclList,sysAllAclList,sysAclModuleDtoList,isAdmin,roleSysAclList);

            return sysAclModuleDtoList ;
        }

        return null ;
    }

    public List<SysAclModuleDto> listToTree(List<SysAclModuleDto> sysAclModuleList){
        if(CollectionUtils.isEmpty(sysAclModuleList)){
            return null ;
        }

        Multimap<String,SysAclModuleDto> levelDtoMultimap = ArrayListMultimap.create();

        List<SysAclModuleDto> rootList = Lists.newArrayList();
        for(SysAclModuleDto dto : sysAclModuleList){
            levelDtoMultimap.put(dto.getLevel(),dto);
            if(LevelUtils.getRoot().equals(dto.getLevel())){
                rootList.add(dto);
            }
        }
        //排序
        Collections.sort(rootList, new SysModuleComparator());

        transformDeptTree(rootList , LevelUtils.getRoot() , levelDtoMultimap);

        return rootList ;
    }
    /*
    * 用层级的好处是不止可以表示父子关系,所有的后代或者祖先层级都可以表示
    * */
    public void transformDeptTree(List<SysAclModuleDto> levelList, String level, Multimap<String, SysAclModuleDto> levelDtoMultimap) {
        for(SysAclModuleDto sysModuleLevelDto : levelList){
            String nextLevel = LevelUtils.calculateLevel(level,sysModuleLevelDto.getId());
            List<SysAclModuleDto> sysAclModuleList = (List<SysAclModuleDto>)levelDtoMultimap.get(nextLevel);
            if(!CollectionUtils.isEmpty(sysAclModuleList)){

                Collections.sort(sysAclModuleList,new SysModuleComparator());

                sysModuleLevelDto.setSysAclModuleDtoList(sysAclModuleList);

                transformDeptTree(sysAclModuleList,nextLevel,levelDtoMultimap);
            }
        }
    }


    public  class SysModuleComparator implements Comparator<SysAclModuleDto> {

        @Override
        public int compare(SysAclModuleDto o1, SysAclModuleDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    }

    public boolean isAdmin(SysUser sysUser){
        if( sysUser != null && Integer.valueOf(0).equals(sysUser.getDeptId())){
            return true ;
        }
        return false ;

    }

    //获取当前用户下的所有权限
    //获取用户角色 user -> AclList
    public List<SysAcl> getAclByUser(SysUser su){
        if(su  ==  null ){
            return  null  ;
        }
        return sysAclService.getAclByUser(su);
    }
    //把acl转为acldto并设置到aclmodule下
    public List<SysAclModuleDto> setAclForAclModule(List<SysAcl> userSysAclList ,List<SysAcl> sysAllAclList , List<SysAclModuleDto> sysAclModuleDtoList , boolean isAdmin , List<SysAcl> roleSysAclList){

        Multimap<Integer,SysAclDto> multimap = ArrayListMultimap.create();
        if(isAdmin){

            for(SysAcl sysAcl : sysAllAclList){

                SysAclDto sysAclDto = SysAclDto.adapt(sysAcl);

                sysAclDto.setChecked(isAdmin);
                sysAclDto.setHasAcl(isAdmin);

                multimap.put(sysAclDto.getAclModuleId(),sysAclDto);
            }
        }else{
            for(SysAcl sysAcl : sysAllAclList){

                SysAclDto sysAclDto = SysAclDto.adapt(sysAcl);

                if(!CollectionUtils.isEmpty(userSysAclList) && userSysAclList.contains(sysAcl)){
                    sysAclDto.setHasAcl(true);
                }
                if(!CollectionUtils.isEmpty(roleSysAclList) && roleSysAclList.contains(sysAcl)){
                    sysAclDto.setChecked(true);
                }
                multimap.put(sysAclDto.getAclModuleId(),sysAclDto);
            }

        }
        //根据moduleId 设置  sysAclModuleDtoList
        for(SysAclModuleDto sysAclModuleDto : sysAclModuleDtoList){

            Integer aclModuleId =  sysAclModuleDto.getId();

            List<SysAclDto> sysAclDtos = (List<SysAclDto>) multimap.get(aclModuleId);

            Collections.sort(sysAclDtos, new Comparator<SysAclDto>() {
                @Override
                public int compare(SysAclDto o1, SysAclDto o2) {
                    return o1.getSeq() - o2.getSeq();
                }
            });

            sysAclModuleDto.setSysAclDtoList(sysAclDtos);
        }
        return sysAclModuleDtoList;
    }
}