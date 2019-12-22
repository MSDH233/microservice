package com.fujinran.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_role_acl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRoleAcl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "acl_id")
    private Integer aclId;

    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operate_ip")
    private String operateIp;

    private static final long serialVersionUID = 1L;
}