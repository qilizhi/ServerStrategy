package com.dz.repository;

import java.util.List;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.Region;

public interface RegionReposity extends BaseRepository<Region, Long> {
	List<Region> findByCountryId(Long countryId);

}
