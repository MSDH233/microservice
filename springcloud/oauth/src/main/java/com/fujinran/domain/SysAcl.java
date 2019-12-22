package com.fujinran.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_acl")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class SysAcl implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String name;

    @Column(name = "acl_module_id")
    private Integer aclModuleId;

    private String url;

    private Integer type;

    private Integer status;

    private Integer seq;

    private String remark;

    private String operator;

    @Column(name = "operator_time")
    @DateTimeFormat(pattern="yyyy-mm-dd hh:mm:ss")
    private Date operatorTime;

    @Column(name = "operator_ip")
    private String operatorIp;

    private static final long serialVersionUID = 1L;
}