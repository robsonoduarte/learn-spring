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
@JsonDeserialize(builder = GeoLocation.GeoLocationBuilder.class)
public class GeoLocation {

	private final String precision;
	private final Location location;


	@JsonPOJOBuilder(withPrefix="")
	public static final class GeoLocationBuilder{}
}
