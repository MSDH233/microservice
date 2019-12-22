package com.fujinran.domain;

import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "sys_log")
@Builder
public class SysLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer type;

    @Column(name = "target_id")
    private Integer targetId;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    private String operator;

    @Column(name = "operate_time")
    private Date operateTime;

    @Column(name = "operate_ip")
    private String operateIp;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return target_id
     */
    public Integer getTargetId() {
        return targetId;
    }

    /**
     * @param targetId
     */
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    /**
     * @return old_value
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * @param oldValue
     */
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * @return new_value
     */
    public String getNewValue() {
        return newValue;
    }

    /**
     * @param newValue
     */
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    /**
     * @return operator
     */
    public String getOperator() {
        return operator;
    }

    /**
     * @param operator
     */
    public void setOperator(String operator) {
        this.operator = operator;
    }

    /**
     * @return operate_time
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return operate_ip
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * @param operateIp
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }
}