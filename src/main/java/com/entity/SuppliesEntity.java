package com.entity;

import com.annotation.ColumnInfo;

import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 运动信息
 *
 * @author
 * @email
 */
@TableName("supplies")
public class SuppliesEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public SuppliesEntity() {

	}

	public SuppliesEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 运动标题
     */
    @ColumnInfo(comment="运动标题",type="varchar(200)")
    @TableField(value = "supplies_name")

    private String suppliesName;


    /**
     * 运动照片
     */
    @ColumnInfo(comment="运动照片",type="varchar(200)")
    @TableField(value = "supplies_photo")

    private String suppliesPhoto;


    /**
     * 运动类型
     */
    @ColumnInfo(comment="运动类型",type="int(11)")
    @TableField(value = "supplies_types")

    private Integer suppliesTypes;


    /**
     * 消耗
     */
    @ColumnInfo(comment="消耗",type="int(11)")
    @TableField(value = "zuowei_number")

    private Integer zuoweiNumber;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "supplies_delete")

    private Integer suppliesDelete;


    /**
     * 详情
     */
    @ColumnInfo(comment="详情",type="longtext")
    @TableField(value = "supplies_content")

    private String suppliesContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：运动标题
	 */
    public String getSuppliesName() {
        return suppliesName;
    }
    /**
	 * 设置：运动标题
	 */

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName;
    }
    /**
	 * 获取：运动照片
	 */
    public String getSuppliesPhoto() {
        return suppliesPhoto;
    }
    /**
	 * 设置：运动照片
	 */

    public void setSuppliesPhoto(String suppliesPhoto) {
        this.suppliesPhoto = suppliesPhoto;
    }
    /**
	 * 获取：运动类型
	 */
    public Integer getSuppliesTypes() {
        return suppliesTypes;
    }
    /**
	 * 设置：运动类型
	 */

    public void setSuppliesTypes(Integer suppliesTypes) {
        this.suppliesTypes = suppliesTypes;
    }
    /**
	 * 获取：消耗
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }
    /**
	 * 设置：消耗
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getSuppliesDelete() {
        return suppliesDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setSuppliesDelete(Integer suppliesDelete) {
        this.suppliesDelete = suppliesDelete;
    }
    /**
	 * 获取：详情
	 */
    public String getSuppliesContent() {
        return suppliesContent;
    }
    /**
	 * 设置：详情
	 */

    public void setSuppliesContent(String suppliesContent) {
        this.suppliesContent = suppliesContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Supplies{" +
            ", id=" + id +
            ", suppliesName=" + suppliesName +
            ", suppliesPhoto=" + suppliesPhoto +
            ", suppliesTypes=" + suppliesTypes +
            ", zuoweiNumber=" + zuoweiNumber +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", suppliesDelete=" + suppliesDelete +
            ", suppliesContent=" + suppliesContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
