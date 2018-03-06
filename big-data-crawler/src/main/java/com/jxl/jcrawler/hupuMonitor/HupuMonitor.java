package com.jxl.jcrawler.hupuMonitor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HupuMonitor {
	
	 @Scheduled(fixedDelay = 1000*60*5)
     public void timerRate() {
		 HupuCrawler.getAllPost("");
     }

}
