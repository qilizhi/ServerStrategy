package com.dz.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.Server;

public interface ServerReposity extends BaseRepository<Server, Long> {
	
	/*String querySearchA="select distinct s from Server s left join  s.country co,IN(s.isps) isp,IN(s.servers) server  " +
			"where  co.name like CONCAT('%',:countryName, '%') and s.status='1' " +
			"and s.type.name like CONCAT('%',:strategyType, '%') " +
			"and server.status='1' order by server.priority desc ";
	@Query(querySearchA)
	//@Cacheable
	List<Server> searchByA(@Param("countryName")String country_name,@Param("strategyType")String strategyType);*/
	Page<Server> findByStrategysId(Long strategyId, Pageable pageBounds);
	//Set<Server> findByStrategys_IsDefaultAndStrategys_StatusOrderByPriorityDesc(Integer i,Integer j);
	List<Server> findDistinctServerByStrategys_IsDefaultAndStrategys_StatusOrderByPriorityDesc(Integer i,Integer j);
	List<Server> findDistinctServerByStrategys_IsDefaultAndStrategys_StatusAndStrategys_type_nameOrderByPriorityDesc(
			int i, int j, String type);
	List<Server> findDistinctServerByStatusAndStrategys_IsDefaultAndStrategys_StatusAndStrategys_type_nameOrderByPriorityDesc(
			int i, int j, int k, String type);
	List<Server> findDistinctServerByStatusAndStrategys_IsDefaultAndStrategys_StatusAndStrategys_type_nameOrderByPriorityAsc(
			int i, int j, int k, String type);
}
