package com.venus.base.cfg.annotation.impl;

import java.util.ArrayList;
import java.util.List;

public class CfgCenterComponentMgr {
	private static CfgCenterComponentMgr instance;
	
	private List<CfgCenterUpdComponent> configs = new ArrayList<>();
	
	public static CfgCenterComponentMgr getInstance() {
		if (instance == null) {
			synchronized (CfgCenterComponentMgr.class) {
				if (instance == null) {
					instance = new CfgCenterComponentMgr();
				}
			}
		}
		
		return instance;
	}
	
	public void addConfig(CfgCenterUpdComponent config) {
		configs.add(config);
	}
	
	public List<CfgCenterUpdComponent> getConfigs() {
		return configs;
	}
}
