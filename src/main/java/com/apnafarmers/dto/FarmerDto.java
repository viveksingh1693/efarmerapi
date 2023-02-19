package com.apnafarmers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@JsonPropertyOrder({"farmerId","fName","lName","land","landUnit","city","district","pinCode","media","crops"})
public class FarmerDto {

	private Long farmerId;

	private String fName;

	private String lName;

	private Long land;

	private String landUnit;

	private String city;

	private String district;

	private String pinCode;

	private List<MediaDto> media;

	private List<CropDto> crops;
}
