package com.apnafarmers.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crop")
@JsonInclude(Include.NON_EMPTY)
public class Crop {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long cropTypeId;

	private String cropType;

	private String name;

	private Double rate;

	private Long quantity;

	private String quantityUnit;

	private String land;

	private String landUnit;

	private String city;

	private String district;

	private String pinCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Farmer farmer;

}
