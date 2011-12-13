package com.wcs.common.model;// default package
// Generated 2011-7-14 13:50:57 by Hibernate Tools 3.2.4.GA


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wcs.base.entity.BaseEntity;

/**
 * Dict generated by hbm2java
 */
@Entity
@Table(name="dict")
public class Dict extends BaseEntity {
     private String code;
     private String name;
     private String value;
     private String parentCode;

    public Dict() {
    }

	
    public Dict(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public Dict(String code, String name, String value, String parentCode) {
       this.code = code;
       this.name = name;
       this.value = value;
       this.parentCode = parentCode;
    }

    //@Override
    @Transient
    public String getDisplayText() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Column(name="CODE", nullable=false, length=30)
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    
    @Column(name="NAME", nullable=false, length=30)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="VALUE", length=30)
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    
    @Column(name="PARENT_CODE", length=30)
    public String getParentCode() {
        return this.parentCode;
    }
    
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}


