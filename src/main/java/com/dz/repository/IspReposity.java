package com.dz.repository;

import java.util.List;

import com.dz.base.repository.base.BaseRepository;
import com.dz.entities.Isp;

public interface IspReposity extends BaseRepository<Isp, Long> {

	List<Isp> findByCountryId(Long countryId);

}
