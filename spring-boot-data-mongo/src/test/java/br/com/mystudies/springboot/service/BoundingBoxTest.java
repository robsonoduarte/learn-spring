package br.com.mystudies.springboot.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.mystudies.springboot.domain.Location;
import br.com.mystudies.springboot.service.BoundingBox;

public class BoundingBoxTest {


	@InjectMocks
	private BoundingBox service;


	@Mock
	private Location location;


	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}


	@Test
	public void shouldReturnTrueWhenLocationIsInMinOfBoundingBox() {
		when(location.getLat()).thenReturn(-23.568704); // min lat
		when(location.getLon()).thenReturn(-46.693419); // min lon

		assertThat(service.isBoundingbox(location), equalTo(true));

		verify(location, times(2)).getLon();
		verify(location, times(2)).getLat();
	}


	@Test
	public void shouldReturnTrueWhenLocationIsInMaxOfBoundingBox() {
		when(location.getLat()).thenReturn(-23.546686); // max lat
		when(location.getLon()).thenReturn(-46.641146); // max lon

		assertThat(service.isBoundingbox(location), equalTo(true));

		verify(location, times(2)).getLon();
		verify(location, times(2)).getLat();
	}

	@Test
	public void shouldReturnTrueWhenLocationIsInBoundingBox() {
		when(location.getLat()).thenReturn(-23.556782); // lat está no bounding
		when(location.getLon()).thenReturn(-46.686489); // lon está no bounding

		assertThat(service.isBoundingbox(location), equalTo(true));

		verify(location, times(2)).getLon();
		verify(location, times(2)).getLat();
	}


	@Test
	public void shouldReturnFalseWhenLocationIsNotInBoundingBox() {
		when(location.getLat()).thenReturn(-23.556782); // lat está no bounding
		when(location.getLon()).thenReturn(-46.491238); // lon não está no bounding

		assertThat(service.isBoundingbox(location), equalTo(false));

		verify(location, times(2)).getLon();
		verify(location, times(2)).getLat();
	}


	@Test(expected=IllegalArgumentException.class)
	public void shouldShoulThrowOneIllegalArgumentExceptionWheLocationIsNull() {
		service.isBoundingbox(null);
	}


}
