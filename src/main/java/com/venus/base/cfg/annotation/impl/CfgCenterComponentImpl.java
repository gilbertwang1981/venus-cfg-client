package com.venus.base.cfg.annotation.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

import com.venus.base.cfg.annotation.CfgCenterBooleanValue;
import com.venus.base.cfg.annotation.CfgCenterComponent;
import com.venus.base.cfg.annotation.CfgCenterDoubleValue;
import com.venus.base.cfg.annotation.CfgCenterIntegerValue;
import com.venus.base.cfg.annotation.CfgCenterLongValue;
import com.venus.base.cfg.annotation.CfgCenterStringValue;
import com.venus.base.cfg.spring.property.PlaceholderHelper;

@Order(1)
public class CfgCenterComponentImpl extends CfgCenterComponentSpringFactoryImportSelector<CfgCenterComponent> {

	private static Logger logger = LoggerFactory.getLogger(CfgCenterComponentImpl.class);
	
	private PlaceholderHelper placeholderHelper = new PlaceholderHelper();
	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
	}

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		AnnotationAttributes attributes = AnnotationAttributes.fromMap(
				importingClassMetadata.getAnnotationAttributes(getAnnotationClass().getName(), true));
		
		try {
			initApplication(attributes , importingClassMetadata);
		} catch (Exception e) {
			logger.error("初始化对象失败，{}" , e.getMessage());
		}

		return new String[0];
	}
	
	private void initApplication(AnnotationAttributes attributes , AnnotationMetadata metadata) throws SecurityException, ClassNotFoundException {
		String service = resolvePlaceholders(attributes.getString("service"));
		String desc =  resolvePlaceholders(attributes.getString("desc"));
		
		logger.info("初始化配置中心数据 {} {}" , service , desc);
		
		Field [] fields = Class.forName(metadata.getClassName()).getDeclaredFields();
		for (int i = 0;i < fields.length ; i ++) {
			if (fields[i].isAnnotationPresent(CfgCenterStringValue.class)) {
				CfgCenterUpdComponent config = new CfgCenterUpdComponent();
				config.setClazz(metadata.getClassName());
				config.setDefaultValue(fields[i].getAnnotation(CfgCenterStringValue.class).defaultValue());
				config.setDesc(fields[i].getAnnotation(CfgCenterStringValue.class).desc());
				config.setField(fields[i]);
				config.setPropName(fields[i].getAnnotation(CfgCenterStringValue.class).propName());
				config.setService(service);
				
				CfgCenterComponentMgr.getInstance().addConfig(config);
			} else if (fields[i].isAnnotationPresent(CfgCenterIntegerValue.class)) {
				CfgCenterUpdComponent config = new CfgCenterUpdComponent();
				config.setClazz(metadata.getClassName());
				config.setDefaultValue(fields[i].getAnnotation(CfgCenterIntegerValue.class).defaultValue());
				config.setDesc(fields[i].getAnnotation(CfgCenterIntegerValue.class).desc());
				config.setField(fields[i]);
				config.setPropName(fields[i].getAnnotation(CfgCenterIntegerValue.class).propName());
				config.setService(service);
				
				CfgCenterComponentMgr.getInstance().addConfig(config);
			} else if (fields[i].isAnnotationPresent(CfgCenterLongValue.class)) {
				CfgCenterUpdComponent config = new CfgCenterUpdComponent();
				config.setClazz(metadata.getClassName());
				config.setDefaultValue(fields[i].getAnnotation(CfgCenterLongValue.class).defaultValue());
				config.setDesc(fields[i].getAnnotation(CfgCenterLongValue.class).desc());
				config.setField(fields[i]);
				config.setPropName(fields[i].getAnnotation(CfgCenterLongValue.class).propName());
				config.setService(service);
				
				CfgCenterComponentMgr.getInstance().addConfig(config);
			} else if (fields[i].isAnnotationPresent(CfgCenterDoubleValue.class)) {
				CfgCenterUpdComponent config = new CfgCenterUpdComponent();
				config.setClazz(metadata.getClassName());
				config.setDefaultValue(fields[i].getAnnotation(CfgCenterDoubleValue.class).defaultValue());
				config.setDesc(fields[i].getAnnotation(CfgCenterDoubleValue.class).desc());
				config.setField(fields[i]);
				config.setPropName(fields[i].getAnnotation(CfgCenterDoubleValue.class).propName());
				config.setService(service);
				
				CfgCenterComponentMgr.getInstance().addConfig(config);
			} else if (fields[i].isAnnotationPresent(CfgCenterBooleanValue.class)) {
				CfgCenterUpdComponent config = new CfgCenterUpdComponent();
				config.setClazz(metadata.getClassName());
				config.setDefaultValue(fields[i].getAnnotation(CfgCenterBooleanValue.class).defaultValue());
				config.setDesc(fields[i].getAnnotation(CfgCenterBooleanValue.class).desc());
				config.setField(fields[i]);
				config.setPropName(fields[i].getAnnotation(CfgCenterBooleanValue.class).propName());
				config.setService(service);
				
				CfgCenterComponentMgr.getInstance().addConfig(config);
			} else if (fields[i].isAnnotationPresent(Value.class)) {
				Set<String> keys = placeholderHelper.extractPlaceholderKeys(fields[i].getAnnotation(Value.class).value());
				if (keys.isEmpty()) {
				      return;
				}
				
				List<String> keysList = new ArrayList<>(keys);
				
				CfgCenterUpdComponent config = new CfgCenterUpdComponent();
				config.setClazz(metadata.getClassName());
				config.setDefaultValue(fields[i]);
				config.setDesc(Strings.EMPTY);
				config.setField(fields[i]);
				config.setPropName(keysList.get(0));
				config.setService(service);
				
				CfgCenterComponentMgr.getInstance().addConfig(config);
			}
		}
	}
}
