package br.com.mystudies.springboot.service;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.data.domain.PageRequest.of;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.domain.Location;
import br.com.mystudies.springboot.repository.ImmobileRepository;


public class ImmobileServiceTest {



	@InjectMocks
	private ImmobileService service;

	// MOCKS EM COMUM PARA OS TESTES

	@Mock
	private ImmobileRepository immobileRepository;


	@Mock
	private BoundingBox boundingBoxGrupoPazService;


	@Mock
	private ImmobileParameters immobileParameters;


	@Mock
	private Page<Immobile> page;


	@Mock
	private Pageable pageable;


	// MOCKS PARA TESTE DAS REGRAS DE BOUNDING BOX

	@Mock
	private Immobile immobileInBoundingBox; //imóvel no bounding box do grupo paz

	@Mock
	private Immobile immobileOutBoundingBox; // imóvel fora bounding box do grupo paz

	@Mock
	private Immobile immobileWithPricesModified; // imóvel com os preços recalculados

	@Mock
	private Location locationInBoundingBox; // location no bounding box do grupo paz

	@Mock
	private Location locationOutBoundingBox; // location fora do bounding box do grupo paz



	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}



	// TESTE PARA IMOVEIS DE ALUGUEL


	@Test
	public void shouldGetThePropertiesToRentalForPortalPaz() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("paz");
		when(immobileRepository.findAllImmobileToRentalForPaz(of(1, 20))).thenReturn(page);

		Page<Immobile> pageResult =
			  service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, equalTo(page));

		verify(immobileParameters).getPage();
		verify(immobileParameters).getPortal();
		verify(immobileRepository).findAllImmobileToRentalForPaz(of(1, 20));

	}



	@Test
	public void shouldGetThePropertiesToRentalForPortalLaeravivAndApplyCalcInRentalTotalPriceWhenImmobileInBoundingBoxOfGrupoPaz() {


		page = new PageImpl<>(asList(immobileInBoundingBox,immobileOutBoundingBox),pageable, 50L);



		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("laeraviv");
		when(immobileRepository.findAllImmobileToRentalForLaeraviv(of(1, 20))).thenReturn(page);
		when(immobileInBoundingBox.location()).thenReturn(locationInBoundingBox);
		when(immobileOutBoundingBox.location()).thenReturn(locationOutBoundingBox);
		when(immobileInBoundingBox.increaseRentalTotalPrice(50)).thenReturn(immobileWithPricesModified);
		when(boundingBoxGrupoPazService.isBoundingbox(locationInBoundingBox)).thenReturn(true);
		when(boundingBoxGrupoPazService.isBoundingbox(locationOutBoundingBox)).thenReturn(false);

		Page<Immobile> pageResult =
				service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult.getContent(), hasSize(2));
		assertThat(pageResult.getContent(), contains(immobileWithPricesModified,immobileOutBoundingBox));
		assertThat(pageResult.getPageable(), equalTo(pageable));
		assertThat(pageResult.getTotalElements(), equalTo(50L));


		verify(immobileParameters).getPage();
		verify(immobileParameters, times(2)).getPortal();
		verify(immobileRepository).findAllImmobileToRentalForLaeraviv(of(1, 20));
		verify(immobileInBoundingBox).location();
		verify(immobileOutBoundingBox).location();
		verify(immobileInBoundingBox).increaseRentalTotalPrice(50);
		verify(immobileOutBoundingBox, times(0)).increaseRentalTotalPrice(50);
		verify(boundingBoxGrupoPazService).isBoundingbox(locationInBoundingBox);
		verify(boundingBoxGrupoPazService).isBoundingbox(locationOutBoundingBox);

	}


	@Test
	public void shouldReturnOneEmptyPageOImmobileWhenPortalIsNotPazOrLaeraviv() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn(null);

		Page<Immobile> pageResult =
				service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, notNullValue());
		assertThat(pageResult.getContent(), hasSize(0));

		verify(immobileParameters, times(2)).getPortal();
		verify(immobileParameters, times(0)).getPage();
		verify(immobileRepository, times(0)).findAllImmobileToRentalForLaeraviv(of(1, 20));
		verify(immobileRepository, times(0)).findAllImmobileToRentalForPaz(of(1, 20));

	}


	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowOneIllegalArgumentExceptionWheImmobileParametersIsNullToGetPropertiesToRental() {
		service.getPropertiesToRental(null);
	}



	// TESTE PARA IMOVEIS A VENDA



	@Test
	public void shouldGetThePropertiesToSaleForPortalPazAndApplyCalcInPriceWhenImmobileInBoundingBoxOfGrupoPaz() {

		page = new PageImpl<>(asList(immobileInBoundingBox,immobileOutBoundingBox),pageable, 50L);

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("paz");
		when(immobileRepository.findAllImmobileToSaleForPaz(of(1, 20))).thenReturn(page);
		when(immobileInBoundingBox.location()).thenReturn(locationInBoundingBox);
		when(immobileOutBoundingBox.location()).thenReturn(locationOutBoundingBox);
		when(immobileInBoundingBox.decreasePrice(10)).thenReturn(immobileWithPricesModified);
		when(boundingBoxGrupoPazService.isBoundingbox(locationInBoundingBox)).thenReturn(true);
		when(boundingBoxGrupoPazService.isBoundingbox(locationOutBoundingBox)).thenReturn(false);

		Page<Immobile> pageResult =
			  service.getPropertiesToSale(immobileParameters);

		assertThat(pageResult.getContent(), hasSize(2));
		assertThat(pageResult.getContent(), contains(immobileWithPricesModified,immobileOutBoundingBox));
		assertThat(pageResult.getPageable(), equalTo(pageable));
		assertThat(pageResult.getTotalElements(), equalTo(50L));

		verify(immobileParameters).getPage();
		verify(immobileParameters).getPortal();
		verify(immobileRepository).findAllImmobileToSaleForPaz(of(1, 20));
		verify(immobileInBoundingBox).location();
		verify(immobileOutBoundingBox).location();
		verify(immobileInBoundingBox).decreasePrice(10);
		verify(immobileOutBoundingBox, times(0)).decreasePrice(10);
		verify(boundingBoxGrupoPazService).isBoundingbox(locationInBoundingBox);
		verify(boundingBoxGrupoPazService).isBoundingbox(locationOutBoundingBox);

	}


	@Test
	public void shouldGetThePropertiesToSaleForPortalLaeraviv() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("laeraviv");
		when(immobileRepository.findAllImmobileToSaleForLaeraviv(of(1, 20))).thenReturn(page);

		Page<Immobile> pageResult =
				service.getPropertiesToSale(immobileParameters);

		assertThat(pageResult, equalTo(page));

		verify(immobileParameters).getPage();
		verify(immobileParameters, times(2)).getPortal();
		verify(immobileRepository).findAllImmobileToSaleForLaeraviv(of(1, 20));

	}


	@Test
	public void shouldReturnOneEmptyPageOfImmobileWhenPortalIsNotPazOrLaeraviv() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn(null);

		Page<Immobile> pageResult =
				service.getPropertiesToSale(immobileParameters);

		assertThat(pageResult, notNullValue());
		assertThat(pageResult.getContent(), hasSize(0));

		verify(immobileParameters, times(2)).getPortal();
		verify(immobileParameters, times(0)).getPage();
		verify(immobileRepository, times(0)).findAllImmobileToSaleForLaeraviv(of(1, 20));
		verify(immobileRepository, times(0)).findAllImmobileToSaleForPaz(of(1, 20));

	}


	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowOneIllegalArgumentExceptionWheImmobileParametersIsNullToGetPropertiesToSale() {
		service.getPropertiesToSale(null);
	}

}
