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
@JsonDeserialize(builder = Location.LocationBuilder.class)
public class Location {

	private final double lon;
	private final double lat;


	@JsonPOJOBuilder(withPrefix="")
	public static final class LocationBuilder{}
}
