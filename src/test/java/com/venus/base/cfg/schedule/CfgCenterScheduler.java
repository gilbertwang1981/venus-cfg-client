package com.venus.base.cfg.schedule;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venus.base.cfg.impl.CfgService;

@Component
public class CfgCenterScheduler extends Thread {
	
	@Autowired
	private CfgService cfgService;
	
	@PostConstruct
	private void init() {
		start();
	}
	
	public void run() {
		while (true) {
			System.out.println(cfgService.getName() + " " + 
					cfgService.getAge() + " " + cfgService.getNum() + " " + 
					cfgService.getPrice() + " " + cfgService.getIsOpen() + " " + cfgService.getIsEanble()
					+ " " + cfgService.getOk() + " " + cfgService.getData());
			
			try {
				
				Thread.sleep(5000);
				
			} catch (Exception e) {
				
			}
		}
	}
}
