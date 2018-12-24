package com.xiu.fastWeb.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="METHOD_LOCK",uniqueConstraints= {@UniqueConstraint(columnNames="METHOD_NAME")})
public class MethodLock implements Serializable {

    private static final long serialVersionUID = -3489550124933197354L;

    private Long id;//主键(界面隐藏)

    private String methodName;//方法锁名称

    private String description;//描述

    private Long updateTime;//超时时间戳

    private String hostIp;//内网地址

    private Integer status = new Integer(0);//状态，0，默认，正常(不允许手动删除)，1.异常，需要手动删除

    private Date createTime = new Date(); //创建时间

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "MethodLock_SEQ")
    @SequenceGenerator(name="MethodLock_SEQ", allocationSize = 1, sequenceName = "MethodLock_SEQ")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="METHOD_NAME")
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "UPDATE_TIME")
    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name = "HOST_IP")
    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }
    @Column(name="STATUS",columnDefinition="INT default 0")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MethodLock other = (MethodLock) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (methodName == null) {
            if (other.methodName != null)
                return false;
        } else if (!methodName.equals(other.methodName))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MethodLock [id=" + id + ", methodName=" + methodName + ", description=" + description + ", updateTime="
                + updateTime + "]";
    }



}
