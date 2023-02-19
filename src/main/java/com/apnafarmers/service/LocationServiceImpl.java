package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.apnafarmers.dto.Cities;
import com.apnafarmers.dto.Countries;
import com.apnafarmers.dto.Districts;
import com.apnafarmers.dto.States;
import com.apnafarmers.entity.City;
import com.apnafarmers.entity.Country;
import com.apnafarmers.entity.State;
import com.apnafarmers.exception.DataNotFoundException;
import com.apnafarmers.repository.CityRepository;
import com.apnafarmers.repository.CountryRepository;
import com.apnafarmers.repository.StateRepository;
import com.apnafarmers.utils.ApnaFarmersConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LocationServiceImpl implements LocationService {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	@Autowired
	CityRepository cityRepository;

	@Override
	public Countries findAllCountries(Map<String, String> querryParam) {

		List<Country> countries = null;

		String startWith = querryParam.get(ApnaFarmersConstants.STARTWITH);
		String limit = querryParam.get(ApnaFarmersConstants.LIMIT);
		String sort = querryParam.get(ApnaFarmersConstants.SORT);

		log.info("Inside findAllCountries Start");
		if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			log.info("Inside All");
			countries = countryRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(limit)) {
			log.info("Inside Find Country start with {} , Limit {}", startWith, limit);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.ASC, "name"));
			countries = countryRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(sort)) {
			log.info("Inside Find Country start with {} , sortedBy {}", startWith, sort);
			countries = countryRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
		} else if (StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
			log.info("Inside Find Country Limited By {} , sortedBy {}", startWith, sort);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			Page<Country> findAll = countryRepository.findAll(pageLimit);
			countries = findAll.getContent();
		} else if (StringUtils.isNotEmpty(startWith)) {
			log.info("Inside Find Country start with {}", startWith);
			countries = countryRepository.findByNameStartsWithIgnoreCase(startWith);
		} else if (StringUtils.isNotEmpty(limit)) {
			log.info("Fetching Countries Limited by {}", limit);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.ASC, "name"));
			Page<Country> findAll = countryRepository.findAll(pageLimit);
			countries = findAll.getContent();
		} else if (StringUtils.isNotEmpty(sort)) {
			log.info("Fetching Countries Sorted by Desc");
			Pageable pageLimit = Pageable.unpaged();
			countryRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else {
			log.info("Fetching Countries");
			countries = countryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		}

		// Removing states from the countries List
		for (Country country : countries) {
			country.setStates(null);
		}

		log.info("Inside findAllCountries Ends ");

		return Countries.builder().countries(countries).build();
	}

	@Override
	public Countries findCountryById(Long countryId) {

		List<Country> countryList = new ArrayList<>();
		Optional<Country> findById = countryRepository.findById(countryId);
		if (findById.isEmpty()) {
			throw new DataNotFoundException();
		} else {
			countryList.add(findById.get());
		}
		return Countries.builder().countries(countryList).build();
	}

	@Override
	public Countries findCountryByName(String name) {
		Country country = countryRepository.findByNameIgnoreCase(name);
		List<State> states = country.getStates();
		for (State state : states) {
			state.setCities(null);
		}

		List<Country> countryList = new ArrayList<>();
		countryList.add(country);

		return Countries.builder().countries(countryList).build();
	}

	@Override
	public States getAllStates(Map<String, String> querryParam) {
		List<State> states = null;

		String startWith = querryParam.get(ApnaFarmersConstants.STARTWITH);
		String limit = querryParam.get(ApnaFarmersConstants.LIMIT);
		String sort = querryParam.get(ApnaFarmersConstants.SORT);

		log.info("Inside findAllStates Start");
		if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			log.info("Inside All");
			states = stateRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(limit)) {
			log.info("Inside Find States start with {} , Limit {}", startWith, limit);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.ASC, "name"));
			states = stateRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(sort)) {
			log.info("Inside Find States start with {} , sortedBy {}", startWith, sort);
			states = stateRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
		} else if (StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
			log.info("Inside Find States Limited By {} , sortedBy {}", startWith, sort);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			Page<State> findAll = stateRepository.findAll(pageLimit);
			states = findAll.getContent();
		} else if (StringUtils.isNotEmpty(startWith)) {
			log.info("Inside Find States start with {}", startWith);
			states = stateRepository.findByNameStartsWithIgnoreCase(startWith);
		} else if (StringUtils.isNotEmpty(limit)) {
			log.info("Fetching States Limited by {}", limit);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.ASC, "name"));
			Page<State> findAll = stateRepository.findAll(pageLimit);
			states = findAll.getContent();
		} else if (StringUtils.isNotEmpty(sort)) {
			log.info("Fetching States Sorted by Desc");
			Pageable pageLimit = Pageable.unpaged();
			stateRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else {
			log.info("Fetching States");
			states = stateRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		}

		for (State state : states) {
			state.setCities(null);
		}
		log.info("Inside findAllStates Ends ");

		return States.builder().state(states).build();
	}

	@Override
	public States getStatesByName(String name, Map<String, String> querryParam) {
		List<State> states = stateRepository.findByNameStartsWithIgnoreCase(name);

		return States.builder().state(states).build();
	}

	@Override
	public Districts getDistrict(Map<String, String> querryParam) {
		return null;
	}

	@Override
	public Cities getAllCities(Map<String, String> querryParam) {
		List<City> cities = null;

		String startWith = querryParam.get(ApnaFarmersConstants.STARTWITH);
		String limit = querryParam.get(ApnaFarmersConstants.LIMIT);
		String sort = querryParam.get(ApnaFarmersConstants.SORT);

		log.info("Inside getAllCities Start");
		if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			log.info("Inside All");
			cities = cityRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(limit)) {
			log.info("Inside Find City start with {} , Limit {}", startWith, limit);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.ASC, "name"));
			cities = cityRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else if (StringUtils.isNotEmpty(startWith) && StringUtils.isNotEmpty(sort)) {
			log.info("Inside Find City start with {} , sortedBy {}", startWith, sort);
			cities = cityRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
		} else if (StringUtils.isNotEmpty(limit) && StringUtils.isNotEmpty(sort)) {
			log.info("Inside Find City Limited By {} , sortedBy {}", startWith, sort);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.DESC, "name"));
			Page<City> findAll = cityRepository.findAll(pageLimit);
			cities = findAll.getContent();
		} else if (StringUtils.isNotEmpty(startWith)) {
			log.info("Inside Find City start with {}", startWith);
			cities = cityRepository.findByNameStartsWithIgnoreCase(startWith);
		} else if (StringUtils.isNotEmpty(limit)) {
			log.info("Fetching City Limited by {}", limit);
			Pageable pageLimit = PageRequest.of(0, Integer.valueOf(limit), Sort.by(Sort.Direction.ASC, "name"));
			Page<City> findAll = cityRepository.findAll(pageLimit);
			cities = findAll.getContent();
		} else if (StringUtils.isNotEmpty(sort)) {
			log.info("Fetching City Sorted by Desc");
			Pageable pageLimit = Pageable.unpaged();
			cities = cityRepository.findByNameStartsWithIgnoreCaseOrderByName(startWith, pageLimit);
		} else {
			log.info("Fetching City");
			cities = cityRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		}

		return Cities.builder().cities(cities).build();
	}

	@Override
	public Cities getCityByName(String name, Map<String, String> querryParam) {

		List<City> cities = cityRepository.findByNameStartsWithIgnoreCase(name);
		return Cities.builder().cities(cities).build();
	}

}
