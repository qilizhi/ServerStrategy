package com.dz.thread;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dz.entities.City;
import com.dz.entities.Country;
import com.dz.entities.Isp;
import com.dz.entities.Region;
import com.dz.ip.repository.IpReposity;
import com.dz.repository.CityReposity;
import com.dz.repository.CountryReposity;
import com.dz.repository.IspReposity;
import com.dz.repository.RegionReposity;

@Component("dataSysTask")
@Scope("prototype")
public class DataSysTask extends Thread {
	 final static Logger logger= LoggerFactory.getLogger(DataSysTask.class);
	 @Autowired
		private IpReposity ipResposity;
		@Autowired
		private IspReposity ispReposity;
		@Autowired
		private CountryReposity countryReposity;
		@Autowired
		private CityReposity cityReposity;
		@Autowired
		private RegionReposity regionReposity;
		public void test(){
			System.out.println("teeee");
		}
		public void synchronIp() {
			Map<String, Object> map = new HashMap<String, Object>();
			List<String> countNames = ipResposity.findDistCountryNameAll();
			Long countryId=new Long(0);
			Long regionId=new Long(0);
			Long cityId=new Long(0);
			Long ispId=new Long(0);
			for (String countryName : countNames) {
				countryId++;
				if (!countNames.isEmpty()) {
					//根据国家查找地区
					List<String> regionNames = ipResposity.findDistRegionNameAllByCN(countryName);
					List<String> ipsNames=ipResposity.findDistISPAllByCNByName(countryName, "CMCC", "China Telecom", "China Unicom", "China Net");
					//保存国家
					Country country =new Country();
					country.setCode(countryName);
					country.setName(countryName);
					//country.setCountryId(countryId);
					country=countryReposity.save(country);
					//保存运营商
					for(String ipsName:ipsNames){
						// 移动CMCC  电信China Telecom 联通 China Unicom 网通 	China Net
						ispId++;
						Isp isp=new Isp();
						isp.setCountryId(country.getId());
						isp.setName(ipsName);
						isp.setCode(ipsName);
						ispReposity.save(isp);
						
					}
				
					for (String regionName : regionNames) {
						regionId++;
						//保存地区
						Region region=new Region();
						region.setCountryId(country.getId());
						region.setName(regionName);
						region.setCode(regionName);
					//	region.setRegionId(regionId);
						region=regionReposity.save(region);
						if (!regionName.isEmpty()) {
							
							List<String> cityNames = ipResposity
									.findDistCityNameAllByRE(regionName);
							for(String cityName:cityNames){
								cityId++;
								//保存城市
							City city=new City();
							city.setCode(cityName);
							city.setName(cityName);
							//city.setCityId(cityId);
							city.setRegionId(region.getId());
							cityReposity.save(city);
							
							}
							
						}
						
					}
				}
		
			}
			map.put("countCountry", countryId);
			map.put("countRegion", regionId);
			map.put("countCity", cityId);
			logger.info(map.toString());
			//return map;
		}
		
	    @Override
	    public void run() {
	    	synchronIp();
	        logger.info("线程:"+Thread.currentThread()+"运行中.....");
	    }


}
