package com.apnafarmers.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "farmer")
@JsonInclude(Include.NON_EMPTY)
public class Farmer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private Long land;

	private String landUnit;

	private String city;

	private String district;

	private String pincode;

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Media> medias = new HashSet<>();

	public void addMedia(Media media) {
		medias.add(media);
		media.setFarmer(this);
	}

	@OneToMany(mappedBy = "farmer", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Crop> crops = new HashSet<>();

	public void addCrop(Crop crop) {
		crops.add(crop);
		crop.setFarmer(this);
	}
}
