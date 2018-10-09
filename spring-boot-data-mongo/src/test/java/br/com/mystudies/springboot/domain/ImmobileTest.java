package br.com.mystudies.springboot.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.mystudies.springboot.domain.Address;
import br.com.mystudies.springboot.domain.GeoLocation;
import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.domain.Location;
import br.com.mystudies.springboot.domain.PricingInfos;

public class ImmobileTest {


	private Immobile immobile;


	private Location location;


	@Before
	public void setUp() throws Exception {
		location = Location.builder().build();

		immobile = Immobile.builder()
					.pricingInfos(PricingInfos.builder().rentalTotalPrice(100).price(100).build())
					.address(Address.builder().geoLocation(GeoLocation.builder().location(location).build()).build())
					.build();
	}


	@Test
	public void shouldIncreaseTheRentalTotalPriceWithThePercentageAndReturnOneNewImmobile() {
		Immobile immobileIncreasedRentaTotalPrice = immobile.increaseRentalTotalPrice(50);
		assertThat(immobileIncreasedRentaTotalPrice, notNullValue());
		assertThat(immobileIncreasedRentaTotalPrice, not((sameInstance(immobile))));
		assertThat(immobileIncreasedRentaTotalPrice.getPricingInfos().getRentalTotalPrice(), equalTo(150l));
	}


	@Test
	public void shouldDecreaseThePriceWithThePercentageAndReturnOneNewImmobile() {
		Immobile immobileDecreasedRentaTotalPrice = immobile.decreasePrice(10);
		assertThat(immobileDecreasedRentaTotalPrice, notNullValue());
		assertThat(immobileDecreasedRentaTotalPrice, not((sameInstance(immobile))));
		assertThat(immobileDecreasedRentaTotalPrice.getPricingInfos().getPrice(), equalTo(90l));
	}


	@Test(expected=IllegalStateException.class)
	public void shouldThrowOneIllegalStateExceptionWhenImmobileDontHavePrincingInfosToIncreaseRentalTotalPrice() {
		Immobile.builder().build().increaseRentalTotalPrice(50);
	}

	@Test(expected=IllegalStateException.class)
	public void shouldThrowOneIllegalStateExceptionWhenImmobileDontHavePrincingInfosToDecreasePrice() {
		Immobile.builder().build().decreasePrice(10);
	}



	@Test
	public void shouldReturnTheLocationOfImmobile() {
		assertThat(immobile.location(), equalTo(location));
	}


	@Test(expected=IllegalStateException.class)
	public void shouldThrowOneIllegalStateExceptionWhenImmobileDontHaveAddress() {
		Immobile.builder().build().location();
	}


	@Test(expected=IllegalStateException.class)
	public void shouldThrowOneIllegalStateExceptionWhenImmobileDontHaveAddressWithGeoLocation(){
		Immobile.builder()
		.address(Address.builder().build())
		.build()
		.location();
	}


	@Test(expected=IllegalStateException.class)
	public void shouldThrowOneIllegalStateExceptionWhenImmobileDontHaveGeoLocationWithLocation(){
		Immobile.builder()
		.address(Address.builder().geoLocation(GeoLocation.builder().build()).build())
		.build()
		.location();
	}







}
