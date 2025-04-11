package com.entity.view;

import com.annotation.ColumnInfo;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.MeishiEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
* 膳食信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("meishi")
public class MeishiView extends MeishiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 食谱类型的值
	*/
	@ColumnInfo(comment="食谱类型的字典表值",type="varchar(200)")
	private String meishiValue;




	public MeishiView() {

	}

	public MeishiView(MeishiEntity meishiEntity) {
		try {
			BeanUtils.copyProperties(this, meishiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 食谱类型的值
	*/
	public String getMeishiValue() {
		return meishiValue;
	}
	/**
	* 设置： 食谱类型的值
	*/
	public void setMeishiValue(String meishiValue) {
		this.meishiValue = meishiValue;
	}




	@Override
	public String toString() {
		return "MeishiView{" +
			", meishiValue=" + meishiValue +
			"} " + super.toString();
	}
}
