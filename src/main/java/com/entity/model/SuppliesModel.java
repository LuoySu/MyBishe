package com.entity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 运动信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class SuppliesModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 运动标题
     */
    private String suppliesName;


    /**
     * 运动照片
     */
    private String suppliesPhoto;


    /**
     * 运动类型
     */
    private Integer suppliesTypes;


    /**
     * 消耗
     */
    private Integer zuoweiNumber;


    /**
     * 赞
     */
    private Integer zanNumber;


    /**
     * 踩
     */
    private Integer caiNumber;


    /**
     * 逻辑删除
     */
    private Integer suppliesDelete;


    /**
     * 详情
     */
    private String suppliesContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
