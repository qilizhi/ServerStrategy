package com.dz.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

import com.dz.common.util.ApplicationContextProvider;
import com.dz.thread.DataSysTask;

/**
 * 
 * @author qlz
 *
 */
@SpringBootApplication
@ComponentScan("com.dz")
@EnableCaching
/*@ImportResource(locations={"classpath:templateEngine.xml"})*/
public class VideoRun {
	public static void main(String[] args) {
	
		SpringApplication.run(VideoRun.class, args);
		for(String ar:args){
			if("datasyn".equalsIgnoreCase(ar)){
				System.out.println("datasyn 开始数据同步！");
				DataSysTask datasyst=ApplicationContextProvider.getBean("dataSysTask", DataSysTask.class);
				System.out.println(datasyst+" => ");
				 datasyst.synchronIp();
			}
		}
		
	}
}
