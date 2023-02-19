package com.apnafarmers.service;

import java.util.Map;

import com.apnafarmers.dto.Cities;
import com.apnafarmers.dto.Countries;
import com.apnafarmers.dto.Districts;
import com.apnafarmers.dto.States;

public interface LocationService {

	public Countries findAllCountries(Map<String, String> querryParam);

	public Districts getDistrict(Map<String, String> querryParam);

	public Countries findCountryById(Long countryId);

	public Countries findCountryByName(String string);

	public States getStatesByName(String name, Map<String, String> querryParam);

	public States getAllStates(Map<String, String> querryParam);

	public Cities getCityByName(String name, Map<String, String> querryParam);

	public Cities getAllCities(Map<String, String> querryParam);

}
