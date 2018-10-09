package br.com.mystudies.springboot.dataloader;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mystudies.springboot.dataloader.SourceLoader;
import br.com.mystudies.springboot.dataloader.MongoSourceLoader;
import br.com.mystudies.springboot.domain.Immobile;
import br.com.mystudies.springboot.repository.ImmobileRepository;

public class MongoSourceLoaderTest {


	private static final String JSON = "{\"usableAreas\":70,\"listingType\":\"USED\",\"createdAt\":\"2017-04-22T18:39:31.138Z\",\"listingStatus\":\"ACTIVE\",\"id\":\"7baf2775d4a2\",\"parkingSpaces\":1,\"updatedAt\":\"2017-04-22T18:39:31.138Z\",\"owner\":false,\"images\":[\"https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/f908948bf1d9e53d36c4734d296feb99.jpg\",\"https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/bd37e4c09ddd370529489fbbc461dbec.jpg\",\"https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/7b86204f34b751c432c878d607c9d618.jpg\",\"https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/3b1142cffc13c49a1e7ea766c20a1d52.jpg\",\"https://resizedimgs.vivareal.com/crop/400x300/vr.images.sp/f519a27d56696febfb77f6398f4484d8.jpg\"],\"address\":{\"city\":\"\",\"neighborhood\":\"\",\"geoLocation\":{\"precision\":\"NO_GEOCODE\",\"location\":{\"lon\":0,\"lat\":0}}},\"bathrooms\":1,\"bedrooms\":2,\"pricingInfos\":{\"yearlyIptu\":\"60\",\"price\":\"276000\",\"businessType\":\"SALE\",\"monthlyCondoFee\":\"0\"}}";


	@InjectMocks
	private MongoSourceLoader sourceLoader;


	@Mock
	private SourceLoader grupoZapSourceLoader;


	@Mock
	private ImmobileRepository immobileRepository;


	private Immobile immobile;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		immobile =  new ObjectMapper().readValue(JSON, Immobile.class);
	}


	@Test
	public void shouldLoadTheSourceInMogoDb() throws Exception {
		when(grupoZapSourceLoader.loadLines()).thenReturn(asList(JSON));
		sourceLoader.loadSourceInMongo();
		verify(grupoZapSourceLoader).loadLines();
		verify(immobileRepository).save(immobile);
	}



}
