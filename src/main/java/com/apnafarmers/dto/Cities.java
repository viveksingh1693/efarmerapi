package com.apnafarmers.dto;

import java.util.List;

import com.apnafarmers.entity.City;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class Cities {

	private List<City> cities;

}
