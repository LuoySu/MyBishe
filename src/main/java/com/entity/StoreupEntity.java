package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 浏览记录表
 * 数据库通用操作实体类（普通增删改查）
 *
 * @author
 * @email
 * @date 2025-01-14 15:04:48
 */
@TableName("storeup")
public class StoreupEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


    public StoreupEntity() {

    }

    public StoreupEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */

    private Long userid;

    /**
     * 浏览记录id
     */

    private Long refid;

    /**
     * 表名
     */

    private String tablename;

    /**
     * 浏览记录名称
     */

    private String name;

    /**
     * 浏览记录图片
     */

    private String picture;

    /**
     * 运动类型
     */
    private Integer types;

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }


    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 设置：用户id
     */
    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * 获取：用户id
     */
    public Long getUserid() {
        return userid;
    }

    /**
     * 设置：浏览记录id
     */
    public void setRefid(Long refid) {
        this.refid = refid;
    }

    /**
     * 获取：浏览记录id
     */
    public Long getRefid() {
        return refid;
    }

    /**
     * 设置：表名
     */
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    /**
     * 获取：表名
     */
    public String getTablename() {
        return tablename;
    }

    /**
     * 设置：浏览记录名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：浏览记录名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：浏览记录图片
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * 获取：浏览记录图片
     */
    public String getPicture() {
        return picture;
    }

}
