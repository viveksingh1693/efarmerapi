package com.apnafarmers.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CropDto {

	private Long id;

	private String fName;

	private String lName;

	private Long cropTypeId;

	private String cropType;

	private String cropName;

	private Double rate;

	private Long quantity;

	private String quantityUnit;

	private String land;

	private String landUnit;

	private String city;

	private String district;

	private String pinCode;

	private List<MediaDto> media;

}
