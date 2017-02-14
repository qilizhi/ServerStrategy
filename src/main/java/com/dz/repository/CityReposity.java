package com.dz.repository;

import java.util.List;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.City;

public interface CityReposity  extends BaseRepository<City, Long>{
	List<City> findByRegionId(Long regionId);

}
