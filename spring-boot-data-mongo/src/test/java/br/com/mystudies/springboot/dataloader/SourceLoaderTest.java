package br.com.mystudies.springboot.dataloader;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import br.com.mystudies.springboot.dataloader.SourceLoader;

public class SourceLoaderTest {


	@InjectMocks
	private SourceLoader sourceLoader;


	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void shouldLoadTheFileSourceOfGrupoZapAndReturnOneListOfString() throws Exception {
		List<String> source = sourceLoader.loadLines();
		assertThat(source, hasSize(10000));
	}

}
