package com.orcaolineapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.orcaolineapi.resource.produto.SegmentoResource;

@SpringBootTest
public class OrcaolineApiApplicationTests {

	private @Autowired SegmentoResource controller;

	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}

}