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
import br.com.mystudies.springboot.service.BoundingBox;
import br.com.mystudies.springboot.service.ImmobileParameters;
import br.com.mystudies.springboot.service.ImmobileService;


public class ImmobileServiceTest {



	@InjectMocks
	private ImmobileService service;

	// MOCKS EM COMUM PARA OS TESTES

	@Mock
	private ImmobileRepository immobileRepository;


	@Mock
	private BoundingBox boundingBoxGrupoZapService;


	@Mock
	private ImmobileParameters immobileParameters;


	@Mock
	private Page<Immobile> page;


	@Mock
	private Pageable pageable;


	// MOCKS PARA TESTE DAS REGRAS DE BOUNDING BOX

	@Mock
	private Immobile immobileInBoundingBox; //imóvel no bounding box do grupo zap

	@Mock
	private Immobile immobileOutBoundingBox; // imóvel fora bounding box do grupo zap

	@Mock
	private Immobile immobileWithPricesModified; // imóvel com os preços recalculados

	@Mock
	private Location locationInBoundingBox; // location no bounding box do grupo zap

	@Mock
	private Location locationOutBoundingBox; // location fora do bounding box do grupo zap



	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}



	// TESTE PARA IMOVEIS DE ALUGUEL


	@Test
	public void shouldGetThePropertiesToRentalForPortalZap() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("zap");
		when(immobileRepository.findAllImmobileToRentalForZap(of(1, 20))).thenReturn(page);

		Page<Immobile> pageResult =
			  service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, equalTo(page));

		verify(immobileParameters).getPage();
		verify(immobileParameters).getPortal();
		verify(immobileRepository).findAllImmobileToRentalForZap(of(1, 20));

	}



	@Test
	public void shouldGetThePropertiesToRentalForPortalVivaRealAndApplyCalcInRentalTotalPriceWhenImmobileInBoundingBoxOfGrupoZap() {


		page = new PageImpl<>(asList(immobileInBoundingBox,immobileOutBoundingBox),pageable, 50L);



		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("vivareal");
		when(immobileRepository.findAllImmobileToRentalForVivaReal(of(1, 20))).thenReturn(page);
		when(immobileInBoundingBox.location()).thenReturn(locationInBoundingBox);
		when(immobileOutBoundingBox.location()).thenReturn(locationOutBoundingBox);
		when(immobileInBoundingBox.increaseRentalTotalPrice(50)).thenReturn(immobileWithPricesModified);
		when(boundingBoxGrupoZapService.isBoundingbox(locationInBoundingBox)).thenReturn(true);
		when(boundingBoxGrupoZapService.isBoundingbox(locationOutBoundingBox)).thenReturn(false);

		Page<Immobile> pageResult =
				service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult.getContent(), hasSize(2));
		assertThat(pageResult.getContent(), contains(immobileWithPricesModified,immobileOutBoundingBox));
		assertThat(pageResult.getPageable(), equalTo(pageable));
		assertThat(pageResult.getTotalElements(), equalTo(50L));


		verify(immobileParameters).getPage();
		verify(immobileParameters, times(2)).getPortal();
		verify(immobileRepository).findAllImmobileToRentalForVivaReal(of(1, 20));
		verify(immobileInBoundingBox).location();
		verify(immobileOutBoundingBox).location();
		verify(immobileInBoundingBox).increaseRentalTotalPrice(50);
		verify(immobileOutBoundingBox, times(0)).increaseRentalTotalPrice(50);
		verify(boundingBoxGrupoZapService).isBoundingbox(locationInBoundingBox);
		verify(boundingBoxGrupoZapService).isBoundingbox(locationOutBoundingBox);

	}


	@Test
	public void shouldReturnOneEmptyPageOImmobileWhenPortalIsNotZapOrVivaReal() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn(null);

		Page<Immobile> pageResult =
				service.getPropertiesToRental(immobileParameters);

		assertThat(pageResult, notNullValue());
		assertThat(pageResult.getContent(), hasSize(0));

		verify(immobileParameters, times(2)).getPortal();
		verify(immobileParameters, times(0)).getPage();
		verify(immobileRepository, times(0)).findAllImmobileToRentalForVivaReal(of(1, 20));
		verify(immobileRepository, times(0)).findAllImmobileToRentalForZap(of(1, 20));

	}


	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowOneIllegalArgumentExceptionWheImmobileParametersIsNullToGetPropertiesToRental() {
		service.getPropertiesToRental(null);
	}



	// TESTE PARA IMOVEIS A VENDA



	@Test
	public void shouldGetThePropertiesToSaleForPortalZapAndApplyCalcInPriceWhenImmobileInBoundingBoxOfGrupoZap() {

		page = new PageImpl<>(asList(immobileInBoundingBox,immobileOutBoundingBox),pageable, 50L);

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("zap");
		when(immobileRepository.findAllImmobileToSaleForZap(of(1, 20))).thenReturn(page);
		when(immobileInBoundingBox.location()).thenReturn(locationInBoundingBox);
		when(immobileOutBoundingBox.location()).thenReturn(locationOutBoundingBox);
		when(immobileInBoundingBox.decreasePrice(10)).thenReturn(immobileWithPricesModified);
		when(boundingBoxGrupoZapService.isBoundingbox(locationInBoundingBox)).thenReturn(true);
		when(boundingBoxGrupoZapService.isBoundingbox(locationOutBoundingBox)).thenReturn(false);

		Page<Immobile> pageResult =
			  service.getPropertiesToSale(immobileParameters);

		assertThat(pageResult.getContent(), hasSize(2));
		assertThat(pageResult.getContent(), contains(immobileWithPricesModified,immobileOutBoundingBox));
		assertThat(pageResult.getPageable(), equalTo(pageable));
		assertThat(pageResult.getTotalElements(), equalTo(50L));

		verify(immobileParameters).getPage();
		verify(immobileParameters).getPortal();
		verify(immobileRepository).findAllImmobileToSaleForZap(of(1, 20));
		verify(immobileInBoundingBox).location();
		verify(immobileOutBoundingBox).location();
		verify(immobileInBoundingBox).decreasePrice(10);
		verify(immobileOutBoundingBox, times(0)).decreasePrice(10);
		verify(boundingBoxGrupoZapService).isBoundingbox(locationInBoundingBox);
		verify(boundingBoxGrupoZapService).isBoundingbox(locationOutBoundingBox);

	}


	@Test
	public void shouldGetThePropertiesToSaleForPortalVivaReal() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn("vivareal");
		when(immobileRepository.findAllImmobileToSaleForVivaReal(of(1, 20))).thenReturn(page);

		Page<Immobile> pageResult =
				service.getPropertiesToSale(immobileParameters);

		assertThat(pageResult, equalTo(page));

		verify(immobileParameters).getPage();
		verify(immobileParameters, times(2)).getPortal();
		verify(immobileRepository).findAllImmobileToSaleForVivaReal(of(1, 20));

	}


	@Test
	public void shouldReturnOneEmptyPageOfImmobileWhenPortalIsNotZapOrVivaReal() {

		when(immobileParameters.getPage()).thenReturn(1);
		when(immobileParameters.getPortal()).thenReturn(null);

		Page<Immobile> pageResult =
				service.getPropertiesToSale(immobileParameters);

		assertThat(pageResult, notNullValue());
		assertThat(pageResult.getContent(), hasSize(0));

		verify(immobileParameters, times(2)).getPortal();
		verify(immobileParameters, times(0)).getPage();
		verify(immobileRepository, times(0)).findAllImmobileToSaleForVivaReal(of(1, 20));
		verify(immobileRepository, times(0)).findAllImmobileToSaleForZap(of(1, 20));

	}


	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowOneIllegalArgumentExceptionWheImmobileParametersIsNullToGetPropertiesToSale() {
		service.getPropertiesToSale(null);
	}

}
