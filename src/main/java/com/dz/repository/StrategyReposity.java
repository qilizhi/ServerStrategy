package com.dz.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.Server;
import com.dz.entities.Strategy;
import com.dz.enums.Status;

@CacheConfig(cacheNames="Strategy")
public interface StrategyReposity extends BaseRepository<Strategy, Long> {

/*	String querySearch="select distinct s from Strategy s,IN(s.isps) isp,IN(s.servers) server  " +
			"where isp.name like CONCAT('%',:str, '%') " +
			"or server.name like CONCAT('%',:str, '%') " +
			"or server.domain like CONCAT('%',:str, '%') " +
			"or s.city.name like CONCAT('%',:str, '%') " +
			"or s.country.name like CONCAT('%',:str, '%') " +
			"or s.region.name like CONCAT('%',:str, '%') " +
			"or s.status like CONCAT('%',:str, '%')";*/
	String querySearch="select distinct s from Strategy s left join s.city c left join s.region r left join s.country co ,IN(s.isps) isp,IN(s.servers) server  " +
			"where isp.name like CONCAT('%',:str, '%') " +
			"or server.name like CONCAT('%',:str, '%') " +
			"or server.domain like CONCAT('%',:str, '%') " +
			"or c.name like CONCAT('%',:str, '%') " +
			"or co.name like CONCAT('%',:str, '%') " +
			"or r.name like CONCAT('%',:str, '%') " +
			"or s.status like CONCAT('%',:str, '%')";
	String querySearchABCD="select distinct server from Strategy s left join s.city c left join s.region r left join s.country co,IN(s.isps) isp,IN(s.servers) server  " +
			"where isp.name like CONCAT('%',:ispName, '%') " +
			"and c.name like CONCAT('%',:cityName, '%') " +
			"and co.name like CONCAT('%',:countryName, '%') " +
			"and r.name like CONCAT('%',:regionName, '%')  and s.status='1' " +
			"and s.type.name like CONCAT('%',:strategyType, '%') " +
			"and server.status='1' order by server.priority asc ";
	String searchABCDE="select distinct s from Strategy s,IN(s.isps) isp,IN(s.servers) server  " +
			"where isp.name like CONCAT('%',:ispName, '%') " +
			"and s.city.name like CONCAT('%',:cityName, '%') " +
			"and s.country.name like CONCAT('%',:countryName, '%') " +
			"and s.region.name like CONCAT('%',:regionName, '%') " +
			"and s.status=:status " +
			"and s.type.id=:typeId " +
			"order by server.priority asc ";
	String querySearchABC_D="select distinct server from Strategy s left join s.city c left join s.region r left join s.country co,IN(s.isps) isp,IN(s.servers) server  " +
			"where (isp.name like CONCAT('%',:ispName, '%') " +
			"or c.name like CONCAT('%',:cityName, '%')) " +
			"and ( co.name like CONCAT('%',:countryName, '%') " +
			"and r.name like CONCAT('%',:regionName, '%')" +
			"and s.type.name like CONCAT('%',:strategyType, '%') " +
			"and s.status='1' and server.status='1' ) order by server.priority asc ";
	String querySearchAB_D="select distinct server from Strategy s left join s.city c left join s.region r left join s.country co,IN(s.isps) isp,IN(s.servers) server  " +
			"where isp.name like CONCAT('%',:ispName, '%') " +
			"and ( co.name like CONCAT('%',:countryName, '%') " +
			"or r.name like CONCAT('%',:regionName, '%')) and s.status='1' " +
			"and s.type.name like CONCAT('%',:strategyType, '%') " +
			"and server.status='1' order by server.priority asc ";
	String querySearchA="select distinct server from Strategy s left join  s.country co,IN( s.servers) server,IN(s.isps) isp  " +
			"where  co.name like CONCAT('%',:countryName, '%') and s.status='1' " +
			"and s.type.name like CONCAT('%',:strategyType, '%') " +
			"and server.status='1' order by server.priority asc ";
	Page<Strategy> findByServers_NameContainingOrStatusContainingOrIspsContaining(
			String searchString, Pageable pageBounds);

	Page<Strategy> findByCity_NameContaining(String searchString,
			Pageable pageBounds);

	Page<Strategy> findByCountry_NameOrCity_NameContaining(String searchString,
			Pageable pageBounds);

	Page<Strategy> findByCountry_NameContainingOrCity_NameContaining(
			String searchString, Pageable pageBounds);
	//查找默认服务器
	//List<Server> findByServersIsDefault(Integer is);

	@Query(querySearch)
	//@Cacheable
	Page<Strategy> searchByString(@Param("str")String searchString, Pageable pageBounds);

	Page<Strategy> findByStatus(Integer start, Pageable pageBounds);
	@Query(searchABCDE)
	//@Cacheable
	Page<Strategy> searchByABCDE(
			@Param("countryName")String country_name, @Param("regionName")String region_name,@Param("cityName") String city_name,
			@Param("ispName")String isp,@Param("status")Integer status,@Param("typeId")Long typeId,Pageable pageBounds);
	@Query(querySearchABCD)
	//@Cacheable
	List<Server> searchByABCD(
			@Param("countryName")String country_name, @Param("regionName")String region_name,@Param("cityName") String city_name,
			@Param("ispName")String isp,@Param("strategyType")String strategyType);

	@Query(querySearchABC_D)
	//@Cacheable
	List<Server> searchByABC_D(@Param("countryName")String country_name, @Param("regionName")String region_name,@Param("cityName") String city_name,
			@Param("ispName")String isp,@Param("strategyType")String strategyType);
	@Query(querySearchAB_D)
	//@Cacheable
	List<Server> searchByAB_D(@Param("countryName")String country_name, @Param("regionName")String region_name,
			@Param("ispName")String isp,@Param("strategyType")String strategyType);
	@Query(querySearchA)
	//@Cacheable
	List<Server> searchByA(@Param("countryName")String country_name,@Param("strategyType")String strategyType);
	List<Strategy> findByName(String name);

	List<Strategy> findByIsDefault(Integer i);

	//List<Server> findServersByIsDefault(Integer i);

	//List<Strategy> findByIsDefaultAndServers_status(int i, int j);

/*	List<Server> findServersByStrategys_IsDefaultAndStrategys_StatusOrderByPriorityDesc(int i,
			int j);*/
}
