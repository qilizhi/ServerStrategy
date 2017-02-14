package com.dz.service;

import java.io.InputStream;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.dz.model.IpApiModel;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@CacheConfig(cacheNames="Strategy")
public class IpApiService {
	@Value("${ip.api.host}")
    private String host;
	
	@Cacheable
	public IpApiModel getIpInformationByIp(String ip){
		ObjectMapper mapper=new ObjectMapper();
		IpApiModel ipResult=new IpApiModel();
		String url=host+"?ip="+ip;
		//1.获取调接口获取ip 信息
		 HttpGet httpget = new HttpGet(url);
	        try{
	        	CloseableHttpClient httpclient = HttpClients.createDefault();
	              CloseableHttpResponse response = httpclient.execute(httpget);
	              InputStream  data=response.getEntity().getContent();
	              ipResult  = mapper.readValue(data,IpApiModel.class);
	              System.out.println("查询接口URL:"+url);
	        }catch(Exception e){
	            e.printStackTrace();
	            System.out.println("获取接口数据出错！ url:"+url);
	           // return new Result(ResultCode.FAIL,l);
	        }
	       
	       return ipResult;
	}

}
