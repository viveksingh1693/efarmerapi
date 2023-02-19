package com.apnafarmers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.CropDto;
import com.apnafarmers.entity.Crop;
import com.apnafarmers.entity.Farmer;
import com.apnafarmers.exception.ResourceNotFoundException;
import com.apnafarmers.service.CropService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class CropController {

	@Autowired
	CropService cropService;

	@GetMapping("/crops")
	public ResponseEntity<List<CropDto>> getAllCrops(@RequestParam(required = false) String name) {
		List<Crop> crops = new ArrayList<Crop>();

		if (name == null)
			cropService.findAll().forEach(crops::add);
		else
			cropService.findByNameStartsWithIgnoreCase(name).forEach(crops::add);

		if (crops.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<CropDto> cropDtoList = new ArrayList<>();
		for (Crop crop : crops) {

			Farmer farmer = crop.getFarmer();

			CropDto response = CropDto.builder().id(crop.getId()).fName(farmer.getFirstName())
					.lName(farmer.getLastName()).cropTypeId(crop.getCropTypeId()).cropType(crop.getCropType())
					.cropName(crop.getName()).rate(crop.getRate()).quantity(crop.getQuantity())
					.quantityUnit(crop.getQuantityUnit()).land(crop.getLand()).landUnit(crop.getLandUnit())
					.city(crop.getCity()).district(crop.getDistrict()).pinCode(crop.getPinCode()).media(null).build();

			cropDtoList.add(response);
		}

		return new ResponseEntity<>(cropDtoList, HttpStatus.OK);
	}

	@GetMapping("/crops/{id}")
	public ResponseEntity<CropDto> getCropById(@PathVariable("id") long id) {
		Crop crop = cropService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Crop with id = " + id));

		Farmer farmer = crop.getFarmer();
		CropDto cropDto = CropDto.builder().id(crop.getId()).fName(farmer.getFirstName()).lName(farmer.getLastName())
				.cropTypeId(crop.getCropTypeId()).cropType(crop.getCropType()).cropName(crop.getName())
				.rate(crop.getRate()).quantity(crop.getQuantity()).quantityUnit(crop.getQuantityUnit())
				.land(crop.getLand()).landUnit(crop.getLandUnit()).city(crop.getCity()).district(crop.getDistrict())
				.pinCode(crop.getPinCode()).build();

		return new ResponseEntity<>(cropDto, HttpStatus.OK);
	}

	@PostMapping("/crops")
	public ResponseEntity<Crop> addCrop(@RequestBody CropDto request,
			@RequestParam(value = "fid", required = true) Long farmerId) {
		log.info("{}", request);
		Crop crop = new Crop();
		crop.setCropTypeId(request.getCropTypeId());
		crop.setCropType(request.getCropType());
		crop.setName(request.getCropName());
		crop.setRate(request.getRate());
		crop.setQuantity(request.getQuantity());
		crop.setQuantityUnit(request.getQuantityUnit());
		crop.setLand(request.getLand());
		crop.setLandUnit(request.getLandUnit());
		crop.setCity(request.getCity());
		crop.setDistrict(request.getDistrict());
		crop.setPinCode(request.getPinCode());

		Crop save = cropService.saveFarmer(crop);

		return new ResponseEntity<>(save, HttpStatus.CREATED);
	}

	@PutMapping("/crops/{id}")
	public ResponseEntity<Crop> updateCrop(@RequestBody CropDto request,
			@PathVariable(value = "id", required = true) long id) {
		Crop crop = cropService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Not found Crop with id = " + id));

		crop.setCropTypeId(request.getCropTypeId());
		crop.setCropType(request.getCropType());
		crop.setName(request.getCropName());
		crop.setRate(request.getRate());
		crop.setQuantity(request.getQuantity());
		crop.setQuantityUnit(request.getQuantityUnit());
		crop.setLand(request.getLand());
		crop.setLandUnit(request.getLandUnit());
		crop.setCity(request.getCity());
		crop.setDistrict(request.getDistrict());
		crop.setPinCode(request.getPinCode());

		return new ResponseEntity<>(cropService.saveFarmer(crop), HttpStatus.OK);
	}

	@DeleteMapping("/crops/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		cropService.deleteById(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/crops")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		cropService.deleteAll();

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
