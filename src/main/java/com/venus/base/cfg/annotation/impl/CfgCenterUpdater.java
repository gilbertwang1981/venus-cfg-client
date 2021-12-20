package com.venus.base.cfg.annotation.impl;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.venus.base.cfg.annotation.CfgCenterComponent;
import com.venus.base.cfg.consts.CfgServiceConsts;
import com.venus.base.cfg.utils.HttpUtils;

@Component
public class CfgCenterUpdater {
	private static Logger logger = LoggerFactory.getLogger(CfgCenterUpdater.class);
	
	private static final Integer INIT_DELAY = 10;
	private static final Integer INTERVAL = 2000;
	
	private String url;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@PostConstruct
	private void init() {
		url = System.getenv("CFG_PROD_URL");
		if (url == null) {
			url = CfgServiceConsts.CFG_SERVICE_GET_CFG_URL;
		}
		
		new Timer().scheduleAtFixedRate(new TimerTask() {
			public void run() {
				Map<String , Object> beans = applicationContext.getBeansWithAnnotation(CfgCenterComponent.class);
				List<CfgCenterUpdComponent> configs = CfgCenterComponentMgr.getInstance().getConfigs();
				for (CfgCenterUpdComponent config : configs) {
					for (Map.Entry<String, Object> entry : beans.entrySet()) {	
						if (config.getClazz().equals(entry.getValue().getClass().getName())) {
							config.getField().setAccessible(true);
							try {								
								String param = "service=" + config.getService() + "&key=" + config.getPropName();
								
								String response = HttpUtils.sendHttpGet(url , param);
								if (!Strings.isBlank(response)) {
									if (config.getField().getType().getName().equalsIgnoreCase(String.class.getName())) {
										config.getField().set(entry.getValue() , response.trim());
									} else if (config.getField().getType().getName().equalsIgnoreCase(Integer.class.getName())) {
										config.getField().set(entry.getValue() , Integer.valueOf(response.trim()));
									} else if (config.getField().getType().getName().equalsIgnoreCase(Long.class.getName())) {
										config.getField().set(entry.getValue() , Long.valueOf(response.trim()));
									} else if (config.getField().getType().getName().equalsIgnoreCase(Double.class.getName())) {
										config.getField().set(entry.getValue() , Double.valueOf(response.trim()));
									} else if (config.getField().getType().getName().equalsIgnoreCase(Boolean.class.getName())) {
										if (response.trim().equals("0")) {
											config.getField().set(entry.getValue() , Boolean.FALSE);
										} else {
											config.getField().set(entry.getValue() , Boolean.TRUE);
										}
									} else {
										config.getField().set(entry.getValue() , config.getDefaultValue());
									}
								} else {
									config.getField().set(entry.getValue() , config.getDefaultValue());
								}
							} catch (Exception e) {
								logger.error("设置属性失败，{}" , e.getMessage());
							}
							break;
						}
					}
				}
			}
		} , INIT_DELAY , INTERVAL); 
	}
}
