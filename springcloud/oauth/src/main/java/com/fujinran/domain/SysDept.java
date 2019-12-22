package com.fujinran.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_dept")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysDept implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String level;

    private Integer seq;

    private String remark;

    @Column(name = "parentId")
    private Integer parentId;

    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operateIp")
    private String operateip;

    private static final long serialVersionUID = 1L;
}