package com.entity.model;

import java.io.Serializable;


/**
 * 浏览记录表
 * 接收传参的实体类
 * （实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 *
 * @author
 * @email
 * @date 2025-01-14 15:04:48
 */
public class StoreupModel implements Serializable {
    private static final long serialVersionUID = 1L;


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
