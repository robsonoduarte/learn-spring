package br.com.mystudies.springboot.controller;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.service.ImmobileParameters;
import br.com.mystudies.springboot.service.ImmobileService;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ImmobileControllerTest {

	private MockMvc mockMvc;

	private Page<Immobile> page;

	@Autowired
	private ImmobileController immobileController;

	@MockBean
	private ImmobileService immobileService;


	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(this.immobileController).build();
		page = new PageImpl<>(asList(Immobile.builder().id("123").build()), of(1, 20), 100);
	}


	@Test
	public void shouldGETThePropertiesToSaleInEndPointOfTheController() throws Exception {

		ImmobileParameters parameters =
				ImmobileParameters.builder().page(1).portal("laeraviv").build();

		when(immobileService.getPropertiesToSale(parameters)).thenReturn(page);

		mockMvc.perform(get("/properties/sale?portal=laeraviv&page=1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.content", hasSize(1)))
			.andExpect(jsonPath("$.content[0].id", is("123")))
			.andExpect(jsonPath("$.pageable.pageNumber", is(1)))
			.andExpect(jsonPath("$.pageable.pageSize", is(20)))
			.andExpect(jsonPath("$.totalElements", is(100)))
			.andExpect(jsonPath("$.totalPages", is(5)));


		verify(immobileService).getPropertiesToSale(parameters);
	}




	@Test
	public void shouldGETThePropertiesToRentalInEndPointOfTheController() throws Exception {

		ImmobileParameters parameters =
				ImmobileParameters.builder().page(0).portal("paz").build();

		when(immobileService.getPropertiesToRental(parameters)).thenReturn(page);

		mockMvc.perform(get("/properties/rental?portal=paz&page=0"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.content", hasSize(1)))
		.andExpect(jsonPath("$.content[0].id", is("123")))
		.andExpect(jsonPath("$.pageable.pageNumber", is(1)))
		.andExpect(jsonPath("$.pageable.pageSize", is(20)))
		.andExpect(jsonPath("$.totalElements", is(100)))
		.andExpect(jsonPath("$.totalPages", is(5)));


		verify(immobileService).getPropertiesToRental(parameters);
	}




}
