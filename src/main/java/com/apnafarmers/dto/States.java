package com.apnafarmers.dto;

import java.util.List;

import com.apnafarmers.entity.State;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class States {

	private List<State> state;

}
