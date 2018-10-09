package br.com.mystudies.springboot.domain;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder=true)
@AllArgsConstructor(access = PRIVATE)
@JsonDeserialize(builder = PricingInfos.PricingInfosBuilder.class)
public class PricingInfos {

	private final String period;
	private final int yearlyIptu;
	private final long price;
	private final long rentalTotalPrice;
	private final String businessType;
	private final int monthlyCondoFee;


	@JsonPOJOBuilder(withPrefix="")
	public static final class PricingInfosBuilder{}
}
