package com.venus.base.cfg.annotation.impl;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

public abstract class CfgCenterComponentSpringFactoryImportSelector<T> implements DeferredImportSelector, BeanClassLoaderAware , EnvironmentAware  {
	@SuppressWarnings("unchecked")
	private Class<T> annotationClass = (Class<T>) GenericTypeResolver
			.resolveTypeArgument(this.getClass() , CfgCenterComponentSpringFactoryImportSelector.class);
	
	protected  Environment environment;
	
	protected CfgCenterComponentSpringFactoryImportSelector() {
    }
	
	@Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
	
	protected Class<T> getAnnotationClass() {
		return this.annotationClass;
	}
	
	protected String getProperties(String name) {
        if(StringUtils.hasText(name)) {
            return this.environment.getProperty(name);
        }
        return name;
    }

    protected String resolvePlaceholders(String name){
        if(StringUtils.hasText(name)) {
            return this.environment.resolvePlaceholders(name);
        }
        
        return name;
    }
}
