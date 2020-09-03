package com.orcaolineapi.repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.produto.Segmento;
import com.orcaolineapi.repository.produto.SegmentoRepository;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SegmentoRepositoryTest {

	private @Autowired SegmentoRepository repository;
	
	@Test
	public void saveSegmento() {
		Segmento seg = new Segmento("PDS", "PDSGFDSS");
		this.repository.save(seg);
		Assertions.assertThat(seg.getId()).isNotNull();
	}
}