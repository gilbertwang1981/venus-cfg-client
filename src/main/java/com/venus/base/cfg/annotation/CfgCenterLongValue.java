package com.venus.base.cfg.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CfgCenterLongValue {
	/**
	 * 配置中心使用的key的名字
	 * @return
	 */
	String propName();
	
	/**
	 * 配置中心使用的key的缺省值
	 * @return
	 */
	long defaultValue();
	
	/**
	 * 配置中心使用的key的描述
	 * @return
	 */
	String desc();
}
