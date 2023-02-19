package com.apnafarmers.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.Cities;
import com.apnafarmers.dto.Countries;
import com.apnafarmers.dto.Districts;
import com.apnafarmers.dto.States;
import com.apnafarmers.entity.Country;
import com.apnafarmers.service.LocationService;
import com.apnafarmers.utils.ApnaFarmersConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationService locationService;

	@Operation(summary = "Get List of countries ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully fetched the Countries", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Country not found", content = @Content) })
	@RequestMapping(value = { "/countries" })
	public ResponseEntity<Countries> getAllCountries(@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		Countries countries = locationService.findAllCountries(querryParam);

		return new ResponseEntity<>(countries, HttpStatus.OK);
	}

	@Operation(summary = "Get country for by Name ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully fetched the Countries", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Country.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Country not found", content = @Content) })
	@RequestMapping(value = { "/countries/{name}" })
	public ResponseEntity<Countries> getCountryByName(@PathVariable String name,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		log.info("Get Country by Name");
		Countries countries = locationService.findCountryByName(name);

		return new ResponseEntity<>(countries, HttpStatus.OK);
	}

	@GetMapping("/states")
	public States getState(@RequestParam(value = "countryId", required = false) String countryId,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.COUNTRY_ID, countryId);
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		log.info("Inside get All states");
		return locationService.getAllStates(querryParam);
	}

	@GetMapping("/states/{name}")
	public States getStateByName(@PathVariable(value = "name", required = false) String name,
			@RequestParam(value = "countryId", required = false) String countryId,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.COUNTRY_ID, countryId);
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		return locationService.getStatesByName(name, querryParam);
	}

	@GetMapping("/cities")
	public Cities getCities(@RequestParam(value = "stateId", required = false) String stateId,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.STATE_ID, stateId);
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		log.info("Inside get Cities");
		Cities cities = locationService.getAllCities(querryParam);
		return cities;
	}

	@GetMapping("/cities/{name}")
	public Cities getCityByName(@PathVariable(value = "name", required = true) String name,
			@RequestParam(value = "stateId", required = false) String stateId,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.STATE_ID, stateId);
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		return locationService.getCityByName(name, querryParam);
	}

	@GetMapping("/district")
	public Districts getDistrict(@RequestParam(value = "CITY", required = true) String stateId,
			@RequestParam(value = "lang", required = false) String language,
			@RequestParam(value = "st", required = false) String startWith,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "limit", required = false) String limit) {

		Map<String, String> querryParam = new HashMap<>();
		querryParam.put(ApnaFarmersConstants.STATE_ID, stateId);
		querryParam.put(ApnaFarmersConstants.LANGUAGE, language);
		querryParam.put(ApnaFarmersConstants.STARTWITH, startWith);
		querryParam.put(ApnaFarmersConstants.SORT, sort);
		querryParam.put(ApnaFarmersConstants.LIMIT, limit);

		Districts districts = locationService.getDistrict(querryParam);

		return districts;
	}
	
	

}
