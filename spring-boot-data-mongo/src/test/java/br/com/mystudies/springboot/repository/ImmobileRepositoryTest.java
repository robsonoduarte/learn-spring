package br.com.mystudies.springboot.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.repository.ImmobileRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ImmobileRepositoryTest {


	@Autowired
	private ImmobileRepository repository;



	// TESTES PARA FIND DE IMOVEIS LEGIVEIS PARA O VIVAREAL

	@Test
	public void shouldFindAllImmobileThatIsEligibleToRentalForVivaReal() {

		Page<Immobile> pageResult =
				repository.findAllImmobileToRentalForVivaReal(PageRequest.of(0, 20));


		assertThat(pageResult.getContent(), not(empty()));


	    //Um imóvel apenas é elegível pro portal Vivareal:
		// Ele tem lat e lon diferente de 0.
	    //Quando for aluguel e no máximo o valor for de R$ 4.000,00.
		//valor do condomínio não pode ser maior/igual que 30% do valor do aluguel.

		pageResult.forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("RENTAL"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), lessThanOrEqualTo(4000l));
			assertThat(immobile.getPricingInfos().getMonthlyCondoFee(), lessThan((int) (immobile.getPricingInfos().getRentalTotalPrice() * 0.3)));
		});

	}


	@Test
	public void shouldFindAllImmobileThatIsEligibleToSaleForVivaReal() {

		Page<Immobile> pageResult =
				repository.findAllImmobileToSaleForVivaReal(PageRequest.of(0, 20));

		assertThat(pageResult.getContent(), not(empty()));

		//Um imóvel apenas é elegível pro portal Vivareal:
		// Ele tem lat e lon diferente de 0.
		// Quando for venda e no máximo o valor for de R$ 700.000,00.

		pageResult.forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("SALE"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), lessThanOrEqualTo(700000l));
		});

	}




	// TESTES PARA FIND DE IMOVEIS LEGIVEIS PARA O ZAP

	@Test
	public void shouldFindAllImmobileThatIsEligibleToRentalForVivaZap() {

		Page<Immobile> pageResult =
				repository.findAllImmobileToRentalForZap(PageRequest.of(0, 20));


		assertThat(pageResult.getContent(), not(empty()));

	    //Um imóvel apenas é elegível pro portal ZAP:
		// Ele tem lat e lon diferente de 0.
	    //Quando for aluguel e no mínimo o valor for de R$ 3.500,00.

		pageResult.forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("RENTAL"));
			assertThat(immobile.getPricingInfos().getRentalTotalPrice(), greaterThanOrEqualTo(3500l));
		});

	}

	@Test
	public void shouldFindAllImmobileThatIsEligibleToSaleForVivaZap() {

		Page<Immobile> pageResult =
				repository.findAllImmobileToSaleForZap(PageRequest.of(0, 20));


		assertThat(pageResult.getContent(), not(empty()));

		//Um imóvel apenas é elegível pro portal ZAP:
		// Ele tem lat e lon diferente de 0.
        // Quando for venda e no mínimo o valor for de R$ 600.000,00.
		// O valor do metro quadrado (chave usableAreas) não pode ser menor/igual a R$ 3.500,00.

		pageResult.forEach( immobile -> {
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLat(), not(0.0));
			assertThat(immobile.getAddress().getGeoLocation().getLocation().getLon(), not(0.0));
			assertThat(immobile.getPricingInfos().getBusinessType(), equalTo("SALE"));
			assertThat(immobile.getPricingInfos().getPrice(), greaterThanOrEqualTo(600000l));
			assertThat(immobile.getUsableAreas(), greaterThan(3500));
		});
	}

}
