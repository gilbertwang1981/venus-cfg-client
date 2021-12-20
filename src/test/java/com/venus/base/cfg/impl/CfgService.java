package com.venus.base.cfg.impl;

import org.springframework.beans.factory.annotation.Value;

import com.venus.base.cfg.annotation.CfgCenterBooleanValue;
import com.venus.base.cfg.annotation.CfgCenterComponent;
import com.venus.base.cfg.annotation.CfgCenterDoubleValue;
import com.venus.base.cfg.annotation.CfgCenterIntegerValue;
import com.venus.base.cfg.annotation.CfgCenterLongValue;
import com.venus.base.cfg.annotation.CfgCenterStringValue;

@CfgCenterComponent(service = "${cfg.service.name}" , desc = "${cfg.service.desc}")
public class CfgService {
	@CfgCenterStringValue(propName = "name" , defaultValue="王晗" , desc = "名字")
	private String name;
	@CfgCenterLongValue(propName = "num" , defaultValue=32 , desc = "数量")
	private Long num;
	@CfgCenterDoubleValue(propName = "price" , defaultValue=345.65 , desc = "价格")
	private Double price;
	@CfgCenterBooleanValue(propName = "is_open" , defaultValue=false , desc = "是否打开")
	private Boolean isOpen;
	@CfgCenterIntegerValue(propName = "age" , defaultValue=32 , desc = "年龄")
	private Integer age;
	
	@Value("${cfg.service.enable}")
	private String isEanble;
	
	@Value("${cfg.service.ok}")
	private Boolean ok;
	
	@Value("${cfg.service.data}")
	private Integer data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIsEanble() {
		return isEanble;
	}

	public void setIsEanble(String isEanble) {
		this.isEanble = isEanble;
	}

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}
}
