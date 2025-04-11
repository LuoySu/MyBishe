package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 运动信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("supplies")
public class SuppliesVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 运动标题
     */

    @TableField(value = "supplies_name")
    private String suppliesName;


    /**
     * 运动照片
     */

    @TableField(value = "supplies_photo")
    private String suppliesPhoto;


    /**
     * 运动类型
     */

    @TableField(value = "supplies_types")
    private Integer suppliesTypes;


    /**
     * 消耗
     */

    @TableField(value = "zuowei_number")
    private Integer zuoweiNumber;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 逻辑删除
     */

    @TableField(value = "supplies_delete")
    private Integer suppliesDelete;


    /**
     * 详情
     */

    @TableField(value = "supplies_content")
    private String suppliesContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：运动标题
	 */
    public String getSuppliesName() {
        return suppliesName;
    }


    /**
	 * 获取：运动标题
	 */

    public void setSuppliesName(String suppliesName) {
        this.suppliesName = suppliesName;
    }
    /**
	 * 设置：运动照片
	 */
    public String getSuppliesPhoto() {
        return suppliesPhoto;
    }


    /**
	 * 获取：运动照片
	 */

    public void setSuppliesPhoto(String suppliesPhoto) {
        this.suppliesPhoto = suppliesPhoto;
    }
    /**
	 * 设置：运动类型
	 */
    public Integer getSuppliesTypes() {
        return suppliesTypes;
    }


    /**
	 * 获取：运动类型
	 */

    public void setSuppliesTypes(Integer suppliesTypes) {
        this.suppliesTypes = suppliesTypes;
    }
    /**
	 * 设置：消耗
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }


    /**
	 * 获取：消耗
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getSuppliesDelete() {
        return suppliesDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setSuppliesDelete(Integer suppliesDelete) {
        this.suppliesDelete = suppliesDelete;
    }
    /**
	 * 设置：详情
	 */
    public String getSuppliesContent() {
        return suppliesContent;
    }


    /**
	 * 获取：详情
	 */

    public void setSuppliesContent(String suppliesContent) {
        this.suppliesContent = suppliesContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
