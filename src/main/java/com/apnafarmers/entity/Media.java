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
@Table(name = "media")
@JsonInclude(Include.NON_EMPTY)
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String type;

	private String url;

//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "mediaId", referencedColumnName = "id", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Farmer farmer;

//	@ManyToOne
//	@JoinColumn(name = "cropMedia", referencedColumnName = "id", nullable = false)
//	@JsonIgnore
//	private Crop crop;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Media))
			return false;
		return id != null && id.equals(((Media) o).getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
