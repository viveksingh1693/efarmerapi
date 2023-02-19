package com.apnafarmers.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CropResponse {
	
	List<CropDto> crops;

}
