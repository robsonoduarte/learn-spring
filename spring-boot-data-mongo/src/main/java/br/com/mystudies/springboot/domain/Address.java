package br.com.mystudies.springboot.domain;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = PRIVATE)
@JsonDeserialize(builder = Address.AddressBuilder.class)
public class Address {

	private final String city;
	private final String neighborhood;
	private final GeoLocation geoLocation;


	@JsonPOJOBuilder(withPrefix="")
	public static final class AddressBuilder{}
}
