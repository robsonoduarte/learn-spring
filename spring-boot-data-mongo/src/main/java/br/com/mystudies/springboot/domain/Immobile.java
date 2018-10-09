package br.com.mystudies.springboot.domain;

import static lombok.AccessLevel.PRIVATE;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;




@Value
@Builder(toBuilder=true)
@AllArgsConstructor(access = PRIVATE)
@JsonDeserialize(builder = Immobile.ImmobileBuilder.class)
public class Immobile {

	private final String id;
	private final int usableAreas;
	private final String listingType;
	private final Date createdAt;
	private final String listingStatus;
	private final int parkingSpaces;
	private final Date updatedAt;
	private final boolean owner;
	private final List<String> images;
	private final Address address;
	private final int bathrooms;
	private final int bedrooms;
	private final PricingInfos pricingInfos;

	@JsonPOJOBuilder(withPrefix="")
	public static final class ImmobileBuilder{}


	public Immobile increaseRentalTotalPrice(int percentage) {
		validatePrincigInfos();
		long rentalTotalPrice = this.pricingInfos.getRentalTotalPrice();
		long increasedRentalTotalPrice = rentalTotalPrice + ((rentalTotalPrice * percentage) / 100);
		return this.toBuilder()
				.pricingInfos(this.pricingInfos.toBuilder().rentalTotalPrice(increasedRentalTotalPrice).build())
				.build();
	}


	public Immobile decreasePrice(int percentage) {
		validatePrincigInfos();
		long price = this.pricingInfos.getPrice();
		long decreasedPrice = price - ((price * percentage)/100);
		return this.toBuilder()
				.pricingInfos(this.pricingInfos.toBuilder().price(decreasedPrice).build())
				.build();
	}




	public Location location() {
		if(this.address == null
				|| this.address.getGeoLocation() == null
				|| this.address.getGeoLocation().getLocation() == null) {
			throw new IllegalStateException("The Immobile don't have one address or one geolocation or one location");
		}
		return this.address.getGeoLocation().getLocation();
	}




	private void validatePrincigInfos() {
		if(this.pricingInfos == null) {
			throw new IllegalStateException("The Immobile don't have one pricingInfos");
		}
	}



}

