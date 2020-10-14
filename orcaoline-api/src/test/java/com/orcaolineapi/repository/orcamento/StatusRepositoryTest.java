package com.orcaolineapi.repository.orcamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;

import com.orcaolineapi.modelo.orcamento.Status;

@DataJpaTest
@ActiveProfiles("test")

@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StatusRepositoryTest {

	private @Autowired StatusRepository repositoryS;

	@Test
	public void saveStatusWithNotNullIdShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Status sta = new Status(1L);
			this.repositoryS.save(sta);
			assertThat(sta.getId()).isNotNull();
		});
	}
	
	@Test
	public void saveStatusWithInvalidStatusShouldThrowsNoneException() {

		assertDoesNotThrow(() -> {
			Status sta = new Status(9L);
			this.repositoryS.save(sta);
			assertThat(sta.getId()).isNotNull();
		});
	}

	@Test
	public void saveStatusNullShouldThrowsInvalidDataAccessApiUsageException() {

		Throwable exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
			Status sta = null;
			this.repositoryS.save(sta);
		});

		assertEquals(
				"Target object must not be null; nested exception is java.lang.IllegalArgumentException: Target object must not be null",
				exception.getMessage());
	}
}