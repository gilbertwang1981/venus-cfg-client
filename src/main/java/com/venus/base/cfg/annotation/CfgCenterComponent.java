package com.venus.base.cfg.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.venus.base.cfg.annotation.impl.CfgCenterComponentImpl;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Import(CfgCenterComponentImpl.class)
public @interface CfgCenterComponent {
	/**
	 * 服务名字
	 * @return
	 */
	String service();
	
	/**
	 * 服务描述
	 * @return
	 */
	String desc();
}
