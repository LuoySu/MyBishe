package com.entity.view;

import com.annotation.ColumnInfo;
import com.entity.SuppliesEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;

/**
* 运动信息
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("supplies")
public class SuppliesView extends SuppliesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 运动类型的值
	*/
	@ColumnInfo(comment="运动类型的字典表值",type="varchar(200)")
	private String suppliesValue;




	public SuppliesView() {

	}

	public SuppliesView(SuppliesEntity suppliesEntity) {
		try {
			BeanUtils.copyProperties(this, suppliesEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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




	@Override
	public String toString() {
		return "SuppliesView{" +
			", suppliesValue=" + suppliesValue +
			"} " + super.toString();
	}
}
