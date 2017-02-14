package com.dz.ip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dz.ip.entities.Ip2location;

public interface IpReposity extends JpaRepository<Ip2location,Integer> {
	
	@Query("select DISTINCT ip.countryName from Ip2location ip where ip.countryName not like '-'")
	public List<String> findDistCountryNameAll();
	@Query("select DISTINCT ip.regionName from Ip2location ip where ip.countryName=:countryName and ip.regionName not like '-'")
	public List<String> findDistRegionNameAllByCN(@Param("countryName")String countryName);
	@Query("select DISTINCT ip.cityName from Ip2location ip where ip.regionName=:regionName and ip.cityName not like '-'")
	public List<String> findDistCityNameAllByRE(@Param("regionName")String regionName);
	@Query("select DISTINCT ip.isp from Ip2location ip where ip.countryName=:countryName and ip.isp not like '-'")
	public List<String> findDistISPAllByCN(@Param("countryName")String cn);
	@Query("select DISTINCT ip.isp from Ip2location ip where ip.countryName=:countryName and (ip.isp like CONCAT('%',:cmcc, '%') or ip.isp like CONCAT('%',:telecom, '%') or ip.isp like CONCAT('%',:unicom, '%') or ip.isp like CONCAT('%',:net, '%') )")
	public List<String> findDistISPAllByCNByName(@Param("countryName")String cn,@Param("cmcc")String cmcc,@Param("telecom")String telecom,@Param("unicom")String unicom,@Param("net")String net);

}
