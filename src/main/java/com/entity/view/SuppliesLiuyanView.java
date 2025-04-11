package com.entity.view;

import com.annotation.ColumnInfo;
import com.entity.SuppliesLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
* 运动留言
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("supplies_liuyan")
public class SuppliesLiuyanView extends SuppliesLiuyanEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表

	//级联表 用户
		/**
		* 用户姓名
		*/

		@ColumnInfo(comment="用户姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 头像
		*/

		@ColumnInfo(comment="头像",type="varchar(255)")
		private String yonghuPhoto;
		/**
		* 用户手机号
		*/

		@ColumnInfo(comment="用户手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 用户出生年月
		*/

		@ColumnInfo(comment="用户出生年月",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 假删
		*/

		@ColumnInfo(comment="假删",type="int(11)")
		private Integer yonghuDelete;
	//级联表 运动信息
		/**
		* 运动标题
		*/

		@ColumnInfo(comment="运动标题",type="varchar(200)")
		private String suppliesName;
		/**
		* 运动照片
		*/

		@ColumnInfo(comment="运动照片",type="varchar(200)")
		private String suppliesPhoto;
		/**
		* 运动类型
		*/
		@ColumnInfo(comment="运动类型",type="int(11)")
		private Integer suppliesTypes;
			/**
			* 运动类型的值
			*/
			@ColumnInfo(comment="运动类型的字典表值",type="varchar(200)")
			private String suppliesValue;
		/**
		* 消耗
		*/

		@ColumnInfo(comment="消耗",type="int(11)")
		private Integer zuoweiNumber;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer suppliesDelete;
		/**
		* 详情
		*/

		@ColumnInfo(comment="详情",type="longtext")
		private String suppliesContent;



	public SuppliesLiuyanView() {

	}

	public SuppliesLiuyanView(SuppliesLiuyanEntity suppliesLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, suppliesLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	//级联表的get和set 用户

		/**
		* 获取： 用户姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 用户姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 用户手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 用户手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 用户出生年月
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 用户出生年月
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 假删
		*/
		public Integer getYonghuDelete() {
			return yonghuDelete;
		}
		/**
		* 设置： 假删
		*/
		public void setYonghuDelete(Integer yonghuDelete) {
			this.yonghuDelete = yonghuDelete;
		}
	//级联表的get和set 运动信息

		/**
		* 获取： 运动标题
		*/
		public String getSuppliesName() {
			return suppliesName;
		}
		/**
		* 设置： 运动标题
		*/
		public void setSuppliesName(String suppliesName) {
			this.suppliesName = suppliesName;
		}

		/**
		* 获取： 运动照片
		*/
		public String getSuppliesPhoto() {
			return suppliesPhoto;
		}
		/**
		* 设置： 运动照片
		*/
		public void setSuppliesPhoto(String suppliesPhoto) {
			this.suppliesPhoto = suppliesPhoto;
		}
		/**
		* 获取： 运动类型
		*/
		public Integer getSuppliesTypes() {
			return suppliesTypes;
		}
		/**
		* 设置： 运动类型
		*/
		public void setSuppliesTypes(Integer suppliesTypes) {
			this.suppliesTypes = suppliesTypes;
		}


			/**
			* 获取： 运动类型的值
			*/
			public String getSuppliesValue() {
				return suppliesValue;
			}
			/**
			* 设置： 运动类型的值
			*/
			public void setSuppliesValue(String suppliesValue) {
				this.suppliesValue = suppliesValue;
			}

		/**
		* 获取： 消耗
		*/
		public Integer getZuoweiNumber() {
			return zuoweiNumber;
		}
		/**
		* 设置： 消耗
		*/
		public void setZuoweiNumber(Integer zuoweiNumber) {
			this.zuoweiNumber = zuoweiNumber;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getSuppliesDelete() {
			return suppliesDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setSuppliesDelete(Integer suppliesDelete) {
			this.suppliesDelete = suppliesDelete;
		}

		/**
		* 获取： 详情
		*/
		public String getSuppliesContent() {
			return suppliesContent;
		}
		/**
		* 设置： 详情
		*/
		public void setSuppliesContent(String suppliesContent) {
			this.suppliesContent = suppliesContent;
		}


	@Override
	public String toString() {
		return "SuppliesLiuyanView{" +
			", suppliesName=" + suppliesName +
			", suppliesPhoto=" + suppliesPhoto +
			", zuoweiNumber=" + zuoweiNumber +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", suppliesDelete=" + suppliesDelete +
			", suppliesContent=" + suppliesContent +
			", yonghuName=" + yonghuName +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuDelete=" + yonghuDelete +
			"} " + super.toString();
	}
}
